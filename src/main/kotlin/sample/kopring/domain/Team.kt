package sample.kopring.domain

import javax.persistence.*

@Entity
class Team(
    var name: String,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    var id : Long?= null

    @OneToMany(mappedBy = "team")
    var members : MutableList<Member> = mutableListOf()

    override fun toString(): String {
        return "Team(id=$id, name='$name')"
    }



}