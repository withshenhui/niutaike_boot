package com.vless.data.content.result

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ProductTypeResult  {

    var value:Long = 0

    var label:String = ""

    var children:MutableList<ProductTypeResult> = ArrayList()


}