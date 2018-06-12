package com.vless.data.content.controller

import com.vless.data.common.BizException
import com.vless.data.content.model.News
import com.vless.data.content.model.Product
import com.vless.data.content.query.NewsQuery
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.result.PageResult
import com.vless.data.content.service.NewsServiceAware
import com.vless.data.content.service.ProductServiceAware
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/product")
@Api("产品管理")
class ProductController {

    @Autowired
    lateinit var productService: ProductServiceAware

    @ApiIgnore
    @PostMapping("/save")
    fun save(@RequestBody @Valid product: Product, result:BindingResult):Product{
        if(product.logo!!.startsWith("http"))
            product.logo=product.logo!!.substring(product.logo!!.lastIndexOf("/")+1,product.logo!!.length)
        product.gmtCreate=Date()
        return if(result.hasErrors())
            throw BizException("提交信息有误")
        else
            productService.save(product)
    }

    @ApiIgnore
    @PostMapping("/update")
    fun update(@RequestBody @Valid product:Product, result: BindingResult):Product{
        if(product.logo!!.startsWith("http"))
            product.logo=product.logo!!.substring(product.logo!!.lastIndexOf("/")+1,product.logo!!.length)
        return if(result.hasErrors())
            throw BizException("提交信息有误")
        else
            productService.save(product)
    }

    @ApiIgnore
    @RequestMapping("/remove/{id}",method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id:Long) = productService.delete(id)

    @ApiOperation("产品列表")
    @ApiImplicitParams(
            ApiImplicitParam(name = "title", value = "标题", dataType = "String", required = false, paramType = "query" ),
            ApiImplicitParam(name = "page", value = "页码", dataType = "Long", required = false, paramType = "query" ),
            ApiImplicitParam(name = "limit", value = "每页条数", dataType = "Long", required = false, paramType = "query" )
    )
    @GetMapping("/list")
    fun findPage(@ApiIgnore productQuery: ProductQuery):PageResult = productService.findPage(productQuery)

    @ApiOperation("产品详情")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long):Product = productService.findById(id)

}