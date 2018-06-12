package com.vless.data.content.service

import com.vless.data.content.model.Product
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.result.PageResult

interface ProductServiceAware  {

    fun save(product: Product):Product

    fun delete(id:Long)

    fun findPage(productQuery: ProductQuery):PageResult

    fun findById(id:Long) : Product

}