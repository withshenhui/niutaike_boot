package com.vless.data.content.service

import com.vless.data.content.model.Product
import com.vless.data.content.query.ProductIndexQuery
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.result.PageResult
import com.vless.data.content.result.ProductResult

interface ProductServiceAware  {

    fun save(product: Product):Product

    fun delete(id:Long)

    fun findPage(productQuery: ProductQuery):PageResult

    fun findList(productQuery: ProductIndexQuery): List<ProductResult>

    fun findById(id:Long) : Product

    fun findByProductTypeId(type:Long):List<ProductResult>
}