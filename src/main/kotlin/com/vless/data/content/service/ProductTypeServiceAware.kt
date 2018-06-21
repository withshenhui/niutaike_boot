package com.vless.data.content.service

import com.vless.data.content.model.ProductType
import com.vless.data.content.query.ProductTypeQuery
import com.vless.data.content.result.PageResult

interface ProductTypeServiceAware  {

    fun save(t: ProductType):ProductType

    fun delete(id:Long)

    fun findPage(q: ProductTypeQuery):PageResult

    fun findById(id:Long) : ProductType

    fun findByParent(p:Long): List<ProductType>

    fun findByLabel(name:String):ProductType?
}