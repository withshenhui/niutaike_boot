package com.vless.data.security.model

import com.vless.data.common.BaseEntity
import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name="sec_user")
 class SecUser : BaseEntity() {

    @NotEmpty
    @Column(name="username")
    var username:String = ""

    @NotEmpty
    @Column(name="realname")
    var realname:String = ""

    @NotEmpty
    @Column(name="passwd")
    var passwd:String = ""

    @NotEmpty
    @Column(name="keyt")
    var keyt:String = ""

    @NotEmpty
    @Column(name="phone")
    @Length(min=11,max=11)
    var phone:String = ""

    @NotEmpty
    @Column(name="email")
    @Email
    var email:String = ""

    @NotNull
    var status:Int? = null

    @Column(name="locked")
    var locked:Boolean = false

    @Column(name="token")
    var token:String = ""

    @Column(name="avatar")
    var avatar:String = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
}