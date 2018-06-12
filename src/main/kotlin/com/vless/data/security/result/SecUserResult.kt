package com.vless.data.security.result

import com.fasterxml.jackson.annotation.JsonFormat
import com.vless.data.security.model.SecRole
import com.vless.data.security.service.SecUserServiceAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Component
class SecUserResult {

    var id:Long = 0

    var username:String = ""

    var realname:String = ""

    var passwd:String = ""

    var keyt:String = ""

    var phone:String = ""

    var email:String = ""

    var status: Int = 0

    var locked:Boolean = false

    var token:String = ""

    var avatar:String = ""

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    var gmtCreate: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    var gmtModified: Date? = null

    val roles:SecRole?
        get() {
            return secUserService!!.findRoleByUserId(id)
        }

    constructor()

    @Autowired
    constructor(secUserService:SecUserServiceAware){
        Companion.secUserService= secUserService
    }

    companion object {
        var secUserService:SecUserServiceAware? =null
    }

}