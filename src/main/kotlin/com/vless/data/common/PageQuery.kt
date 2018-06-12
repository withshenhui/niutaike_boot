package com.vless.data.common

import java.io.Serializable

open class PageQuery : Serializable {

    var page:Int = 1

    var limit:Int = 20
}