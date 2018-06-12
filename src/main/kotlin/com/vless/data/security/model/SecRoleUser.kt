package com.vless.data.security.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name="sec_role_user")
class SecRoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long =0

    @NotNull
    @Column(name="role_id")
    var roleId:Long =0

    @NotNull
    @Column(name="user_id")
    var userId:Long = 0
}