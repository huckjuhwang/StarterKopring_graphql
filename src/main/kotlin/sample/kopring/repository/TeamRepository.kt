package sample.kopring.repository

import org.springframework.data.jpa.repository.JpaRepository
import sample.kopring.domain.Team

interface TeamRepository : JpaRepository<Team, Long> {

}