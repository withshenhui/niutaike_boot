package com.vless.data.content.repository

import com.vless.data.content.model.Product
import com.vless.data.content.result.ProductResult
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product,Long> {

    fun findByProductTypeId(type:Long):List<ProductResult>
}