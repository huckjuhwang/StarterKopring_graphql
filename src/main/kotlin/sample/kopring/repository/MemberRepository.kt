package sample.kopring.repository

import org.springframework.data.jpa.repository.JpaRepository
import sample.kopring.domain.Member

interface MemberRepository : JpaRepository<Member, Long> {
}
