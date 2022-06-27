package sample.kopring.domain

import org.assertj.core.api.AbstractComparableAssert
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import sample.kopring.repository.MemberJpaRepository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal class MemberTest {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Test
    fun testEntity(){
        var teamA : Team = Team("teamA")
        var teamB : Team = Team("teamB")
        em.persist(teamA)
        em.persist(teamB)

        var member1: Member = Member("member1", 10, teamA)
        var member2: Member = Member("member2", 20, teamA)
        var member3: Member = Member("member3", 30, teamB)
        var member4: Member = Member("member4", 40, teamB)

        member1.changeTeam(teamA)
        member2.changeTeam(teamA)
        member3.changeTeam(teamB)
        member4.changeTeam(teamB)

        em.persist(member1)
        em.persist(member2)
        em.persist(member3)
        em.persist(member4)

        // 초기화
        em.flush()
        em.clear()

        // 확인
        val members = em.createQuery("select m from Member m", Member::class.java).resultList

        for (member in members) {
            println("==========================")
            println("member = $member")
            println("-> member.team = ${member.team}")
            println("==========================")
        }
    }


    @Test
    fun basicCRUD() {
        val member1 = Member("member1")
        val member2 = Member("member2")

        memberJpaRepository.save(member1)
        memberJpaRepository.save(member2)
//        val findById1 = memberJpaRepository.findById(member1.id).id

        // 단건 조회 검증
        val findById1 = memberJpaRepository.findById(member1.id)
        val findById2 = memberJpaRepository.findById(member2.id)

        assertEquals(findById1, member1)
        assertEquals(findById2, member2)

        // 리스트 조회 검증
//        val all : MutableList<Member?>? = memberJpaRepository.findAll()
//        if (all != null)  assertEquals(all.size, 2L)


        // 카운트 검증
        var count : Long = memberJpaRepository.count()
//        assertEquals(count, 2L)
        println("count = $count")


        // 삭제 검증
        memberJpaRepository.delete(member1)
        memberJpaRepository.delete(member2)

        var deleteCount : Long = memberJpaRepository.count()
        println("deleteCount = $deleteCount")
    }
}