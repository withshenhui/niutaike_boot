package com.vless.data.content.query

import com.vless.data.common.PageQuery

class ProductQuery : PageQuery() {

    var title:String? = null

    var showIndex:Boolean? = null

    var productTypeParentId:Long? = null

    var productTypeId:Long? = null

}