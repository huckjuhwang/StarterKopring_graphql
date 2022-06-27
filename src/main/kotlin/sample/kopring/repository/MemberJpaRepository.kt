package sample.kopring.repository

import org.springframework.stereotype.Repository
import sample.kopring.domain.Member
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberJpaRepository {

    @PersistenceContext
    lateinit var em : EntityManager;

    fun save(member :Member) : Member{
        em.persist(member)
        return member
    }

    fun findById(id: Long): Member {
        return em.find(Member::class.java, id)
    }

    fun delete(member: Member) {
        em.remove(member)
    }

    fun findAll(): MutableList<Member?>? {
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

    fun count(): Long {
        return em.createQuery("select count(m) from Member m", Long::class.java)
            .singleResult
    }
}