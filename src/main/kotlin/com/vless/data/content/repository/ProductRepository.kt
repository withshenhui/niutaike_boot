package com.vless.data.content.repository

import com.vless.data.content.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product,Long> {

}