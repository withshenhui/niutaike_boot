package com.vless.data.content.service

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.vless.data.content.mapper.ProductMapper
import com.vless.data.content.mapper.ProductTypeMapper
import com.vless.data.content.model.ProductType
import com.vless.data.content.query.ProductTypeQuery
import com.vless.data.content.repository.ProductTypeRepository
import com.vless.data.content.result.PageResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductTypeService : ProductTypeServiceAware {

    @Autowired
    lateinit var productTypeRepository: ProductTypeRepository

    @Autowired
    lateinit var productTypeMapper: ProductTypeMapper

    @Autowired
    lateinit var productMapper:ProductMapper

    override fun save(p: ProductType): ProductType = productTypeRepository.save(p)

    override fun delete(id: Long) {
        val p=productTypeRepository.findById(id).get()
        if(p.parent>0){
            productMapper.deleteByProductTypeId(id)
        }else{
            //删除子分类
            val listSon=findByParent(p.id)
            for(son in listSon){
                productTypeRepository.deleteById(son.id)
                productMapper.deleteByProductTypeId(son.id)
            }
        }
        productTypeRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    override fun findPage(q: ProductTypeQuery): PageResult {
        PageHelper.startPage<ProductType>(q.page,q.limit)
        val list:List<ProductType> = productTypeMapper.selectAll(q)
        val pageInfo:PageInfo<ProductType> = PageInfo(list)
        return PageResult(pageInfo.total,list)
    }

    override fun findById(id: Long): ProductType = productTypeRepository.findById(id).get()

    override fun findByParent(p: Long): List<ProductType> {
        return productTypeRepository.findByParent(p)
    }

    override fun findByLabel(name: String): ProductType? {
        return productTypeRepository.findByLabel(name)
    }
}