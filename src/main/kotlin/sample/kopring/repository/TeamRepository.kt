package sample.kopring.repository

import org.springframework.stereotype.Repository
import sample.kopring.domain.Member
import sample.kopring.domain.Team
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class TeamRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun save(team: Team): Team {
        em.persist(team)
        return team
    }

    fun delete(team: Team) {
        em.remove(team)
    }

    fun findAll(): MutableList<Team>? {
        return em.createQuery("select m from Team m", Team::class.java)
            .resultList
    }

    fun findById(id: Long): Team {
        return em.find(Team::class.java, id)
    }

    fun count(): Long {
        return em.createQuery("select count(t) from Team t", Long::class.java)
            .singleResult
    }

}