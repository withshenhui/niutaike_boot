package com.vless.data.security.service

import com.vless.data.security.model.SecRole
import com.vless.data.security.result.SecUserResult

interface SecUserServiceAware {

    fun login(username:String,password:String):SecUserResult

    fun findRoleByUserId(userId:Long):SecRole

    fun findByUsername(username:String):SecUserResult

    fun updateUserPasswd(passwd:String,id:Long)
}