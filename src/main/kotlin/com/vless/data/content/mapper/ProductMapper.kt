package com.vless.data.content.mapper

import com.vless.data.content.model.Product
import com.vless.data.content.query.ProductQuery
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductMapper {

    fun selectAll(productQuery: ProductQuery):List<Product>

    fun selectCount(productQuery: ProductQuery):Int



}