package com.vless.data.content.service

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.vless.data.content.mapper.ProductMapper
import com.vless.data.content.model.Product
import com.vless.data.content.query.ProductIndexQuery
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.repository.ProductRepository
import com.vless.data.content.result.PageResult
import com.vless.data.content.result.ProductResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService : ProductServiceAware {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productMapper: ProductMapper

    override fun save(product: Product): Product = productRepository.save(product)

    override fun delete(id: Long) = productRepository.deleteById(id)

    @Transactional(readOnly = true)
    override fun findPage(productQuery: ProductQuery): PageResult {
        PageHelper.startPage<ProductResult>(productQuery.page,productQuery.limit)
        val list:List<ProductResult> = productMapper.selectAll(productQuery)
        val pageInfo:PageInfo<ProductResult> = PageInfo(list)
        return PageResult(pageInfo.total,list)
    }

    @Transactional(readOnly = true)
    override fun findList(productQuery: ProductIndexQuery): List<ProductResult> {
        return productMapper.selectList(productQuery)
    }

    override fun findById(id: Long): Product = productRepository.findById(id).get()

    override fun findByProductTypeId(type: Long): List<Product> {
        return productRepository.findByProductTypeId(type)
    }

}