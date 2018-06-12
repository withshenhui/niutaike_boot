package com.vless.data.security.controller

import com.vless.data.common.MD5
import com.vless.data.security.form.LoginForm
import com.vless.data.security.result.SecUserResult
import com.vless.data.security.service.SecUserServiceAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class LoginController {

    @Autowired
    lateinit var secUserService:SecUserServiceAware

    @PostMapping("/rest/login")
    fun login(@RequestBody @Valid loginForm: LoginForm, result:BindingResult):SecUserResult {
        loginForm.password=MD5.encode(loginForm.password)
        return secUserService.login(loginForm.username,loginForm.password)
    }

    @PostMapping("/rest/logout")
    fun logout() {
    }
}