package com.vless.data.content.result


class ProductIndexResult {

    var productTypeParentId:Long = 0

    var list:MutableList<ProductNextCat> = ArrayList()

    class ProductNextCat{
        var productTypeId:Long = 0
        var productTypeName:String = ""
        var list:MutableList<ProductResult> = ArrayList()
    }

}