package com.vless.data.security.controller

import com.vless.data.common.BizException
import com.vless.data.common.MD5
import com.vless.data.security.form.PasswdResetForm
import com.vless.data.security.model.SecUser
import com.vless.data.security.result.SecUserResult
import com.vless.data.security.service.SecUserServiceAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class SecUserController {

    @Autowired
    lateinit var secUserService:SecUserServiceAware


    @GetMapping("/{username}")
    fun findByUsername(@PathVariable username:String):SecUserResult = secUserService.findByUsername(username)

    @PutMapping("/passwd")
    fun updatePasswd(@RequestBody form: PasswdResetForm){
        val user=secUserService.findByUsername(form.username)
        if(user?.passwd.equals(MD5.encode(form.oldPasswd))){
            secUserService.updateUserPasswd(MD5.encode(form.password),user.id)
        }else{
            throw BizException("密码修改失败：原密码错误")
        }

    }
}