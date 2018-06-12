package com.vless.data.content.service

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.vless.data.content.mapper.ProductMapper
import com.vless.data.content.model.Product
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.repository.ProductRepository
import com.vless.data.content.result.PageResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService : ProductServiceAware {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productMapper: ProductMapper

    override fun save(product: Product): Product = productRepository.save(product)

    override fun delete(id: Long) = productRepository.deleteById(id)

    override fun findPage(productQuery: ProductQuery): PageResult {
        PageHelper.startPage<Product>(productQuery.page,productQuery.limit)
        val list:List<Product> = productMapper.selectAll(productQuery)
        val pageInfo:PageInfo<Product> = PageInfo(list)
        return PageResult(pageInfo.total,list)
    }

    override fun findById(id: Long): Product = productRepository.findById(id).get()


}