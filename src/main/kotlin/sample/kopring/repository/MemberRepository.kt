package sample.kopring.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sample.kopring.domain.Member

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsername(name: String): List<Member>

    @Query(name = "Member.findByUsername")
    fun findByUsername2(@Param("username") username : String) : List<Member>
}
