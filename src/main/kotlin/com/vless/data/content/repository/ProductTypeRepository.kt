package com.vless.data.content.repository

import com.vless.data.content.model.ProductType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductTypeRepository : CrudRepository<ProductType,Long> {

    fun findByParent(p:Long):List<ProductType>

    fun findByLabel(name:String):ProductType?
}