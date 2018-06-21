package com.vless.data.content.mapper

import com.vless.data.content.model.ProductType
import com.vless.data.content.query.ProductTypeQuery
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductTypeMapper {

    fun selectAll(q: ProductTypeQuery):List<ProductType>

    fun selectCount(q: ProductTypeQuery):Int

}