package com.vless.data.content.result

open class PageResult {

    var code:Int = 0
    var msg:String = ""
    var count:Long? = null
    var data:List<Any>?= null


    constructor(count:Long,data:List<Any>?) {
        this.count = count
        this.data = data
    }

}