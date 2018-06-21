package com.vless.data.content.result

import com.vless.data.common.BaseResult

class ProductResult : BaseResult() {

    var title:String? = null

    var intro:String? = null

    var detail:String? = null

    var logo:String? = null

    var showIndex:Boolean = true

    var seoTitle:String? = null

    var seoDescription:String? = null

    var seoKeyword:String? = null

    var productTypeId:Long = 0

    var productTypeName:String = ""

    var productTypeParentId:Long = 0

    var productTypeParentName:String = ""


}