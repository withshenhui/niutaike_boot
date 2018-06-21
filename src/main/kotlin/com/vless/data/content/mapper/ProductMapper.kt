package com.vless.data.content.mapper

import com.vless.data.content.model.Product
import com.vless.data.content.query.ProductIndexQuery
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.result.ProductResult
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface ProductMapper {

    fun selectAll(productQuery: ProductQuery):List<ProductResult>

    fun selectCount(productQuery: ProductQuery):Int

    fun selectList(productQuery: ProductIndexQuery):List<ProductResult>

    @Delete("delete from ntk_product where product_type_id = #{productTypeId}")
    fun deleteByProductTypeId(@Param("productTypeId") productTypeId:Long)

}