package sample.kopring.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import sample.kopring.repository.MemberRepository
import sample.kopring.repository.TeamRepository
import java.util.*
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired lateinit var memberRepository : MemberRepository;
    @Autowired lateinit var teamRepository: TeamRepository;

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


    @Test
    fun testQuery(){
        var m1 = Member("AAA", 10)
        var m2 = Member("BBB", 20)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val result = memberRepository.findUser("AAA", 10)
        assertThat(result.get(0)).isEqualTo(m1)
    }

    @Test
    fun findUsernameList(){
        var m1 = Member("AAA", 10)
        var m2 = Member("BBB", 20)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val result : List<String> = memberRepository.findUsernameList()

        for (s in result) {
            println("s = ${s}")
        }
    }


    @Test
    fun findMemberDto(){
        var team = Team("teamA")
        teamRepository.save(team)

        var m1 = Member("AAA", 10)
        m1.team = team
        memberRepository.save(m1)

        val result  = memberRepository.findMemberDto()
        for (s in result) {
            println("s = ${s}")
        }
    }

    @Test
    fun findByNames(){
        val m1 = Member("AAA", 10)
        val m2 = Member("BBB", 20)

        memberRepository.save(m1)
        memberRepository.save(m2)

//        val result: List<Member> = memberRepository.findByNames(listOf("AAA", "BBB"))
        val result: List<Member> = memberRepository.findByNames(names = Arrays.asList("AAA", "BBB"))

        for (s in result) {
            println("s = ${s}")
        }
    }

    @Test
    fun returnType() {
        val m1 = Member("AAA", 10)
        val m2 = Member("BBB", 20)
        memberRepository.save(m1)
        memberRepository.save(m2)

        /**
         * Collection은 결과가 없어도, null이 나오지 않고, 빈 컬렉션이 나오게 된다. ( 중요 )
         */
        val listMember: List<Member> = memberRepository.findListByUsername("ddddd")
//        val member: Member = memberRepository.findMemberByUsername("AAA")
//        val optionalMember: Optional<Member> = memberRepository.findOptionalByUsername("AAA")

        println("findMemberByUsername = ${listMember}")
    }
}