package sample.kopring.domain

import javax.persistence.*

@Entity
// namedQuery 는 application 로딩시점에 파싱을 해본다.
// 따라서, 로딩시점에 버그를 잡을 수 있다는 장점이 있다.
@NamedQuery(
    name = "Member.findByUsername",
    query = "select m from Member m where m.username = :username"
)
class Member(
    var username : String,
    var age: Int?= null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    var team: Team?= null
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    var id: Long = 0

    override fun toString(): String {
        return "Member(id=$id, age=$age)"
    }

    fun changeTeam(team: Team) {
        this.team = team
        team.members.add(this)
    }
}
