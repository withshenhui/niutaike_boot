package com.vless.data.security.model

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name="sec_role")
class SecRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long =0

    @NotEmpty
    @Column(name="name")
    var name:String = ""

    @NotNull
    @Column(name="type")
    var type:Int = 1

    @Column(name="memo")
    var memo:String =""

    @NotNull
    @Column(name="parent")
    var parent:Long = 0

    @NotNull
    @Column(name="status")
    var status:Int = 1
}