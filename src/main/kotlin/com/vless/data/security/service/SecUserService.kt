package com.vless.data.security.service

import com.vless.data.common.BizException
import com.vless.data.security.mapper.SecUserMapper
import com.vless.data.security.model.SecRole
import com.vless.data.security.repository.SecUserRepository
import com.vless.data.security.result.SecUserResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SecUserService : SecUserServiceAware {

    @Autowired
    lateinit var secUserRepository: SecUserRepository

    @Autowired
    lateinit var secUserMapper: SecUserMapper

    override fun login(username: String, password: String): SecUserResult {

        val user=secUserMapper.selectByUsername(username)

        return if(user!=null&&user!!.passwd.equals(password)){
            user
        }else{
            throw BizException("登录失败")
        }

    }

    override fun findRoleByUserId(userId: Long): SecRole {
        return secUserMapper.selectRoleByUserId(userId)
    }

    override fun findByUsername(username: String): SecUserResult {
        return secUserMapper.selectByUsername(username)
    }

    override fun updateUserPasswd(passwd: String, id: Long) {
        secUserMapper.updateUserPasswd(passwd,id)
    }

}