package sample.kopring.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sample.kopring.domain.Member
import sample.kopring.dto.MemberDto
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsername(name: String): List<Member>

    @Query(name = "Member.findByUsername")
    fun findByUsername2(@Param("username") username : String) : List<Member>

    @Query("select m from Member m where m.username = :username and m.age = :age")
    fun findUser(@Param("username") username: String, @Param("age") age: Int) : List<Member>

    @Query("select m.username from Member m")
    fun findUsernameList() : List<String>

    @Query("select new sample.kopring.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    fun findMemberDto() : List<MemberDto>

    @Query("select m from Member m where m.username in :names")
    fun findByNames(@Param("names") names: Collection<String>) : List<Member>

    fun findListByUsername(username: String) : List<Member>

    fun findMemberByUsername(username: String) : Member

    fun findOptionalByUsername(username: String) : Optional<Member>
}
