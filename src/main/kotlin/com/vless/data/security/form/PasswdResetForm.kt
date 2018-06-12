package com.vless.data.security.form

import javax.validation.constraints.NotEmpty

class PasswdResetForm {

    @NotEmpty
    var oldPasswd:String = ""

    @NotEmpty
    var password:String = ""

    @NotEmpty
    var username:String = ""
}