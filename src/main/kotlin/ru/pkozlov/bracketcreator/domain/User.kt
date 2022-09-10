package ru.pkozlov.bracketcreator.domain

import javax.persistence.*

@Entity
@Table(
    name = "usr",
    indexes = [Index(name = "usr_username_idx", columnList = "username", unique = true)]
)
@SequenceGenerator(name = "usr_id_seq_generator", sequenceName = "usr_id_seq", allocationSize = 1)
class User(
    @Id val id: Long,

    val username: String,

    var password: String,

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var roles: MutableSet<Role> = mutableSetOf()
) {
    var deleted: Boolean = false

    enum class Role {
        ADMIN
    }
}