package sample.kopring.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import sample.kopring.repository.MemberRepository
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired lateinit var memberRepository : MemberRepository;

    @Test
    fun basicCRUD() {
        val member1 = Member("member1")
        val member2 = Member("member2")

        memberRepository.save(member1)
        memberRepository.save(member2)
//        val findById1 = memberJpaRepository.findById(member1.id).id

        // 단건 조회 검증
        val findById1 = memberRepository.findById(member1.id).get()
        val findById2 = memberRepository.findById(member2.id).get()

        assertThat(findById1).isEqualTo(member1)
        assertThat(findById2).isEqualTo(member2)

        // 리스트 조회 검증
        val findAll = memberRepository.findAll()
        assertThat(findAll.size).isEqualTo(2)

        // 카운트 검증
        var count : Long = memberRepository.count()
        println("count = $count")
        assertThat(count).isEqualTo(2)

        // 삭제 검증
        memberRepository.delete(member1)
        memberRepository.delete(member2)
//
        var deleteCount = memberRepository.count()
        assertThat(deleteCount).isEqualTo(0)
    }

    @Test
    fun testNameQuery(){
        var m1 = Member("AAA", 10)
        var m2 = Member("BBB", 20)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val result: List<Member> = memberRepository.findByUsername2("AAA")
        var findMember :Member = result.get(0)
        assertThat(findMember).isEqualTo(m1)
    }
}