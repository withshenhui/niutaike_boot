package com.vless.data.content.controller

import com.vless.data.common.BizException
import com.vless.data.content.model.News
import com.vless.data.content.model.ProductType
import com.vless.data.content.query.NewsQuery
import com.vless.data.content.query.ProductTypeQuery
import com.vless.data.content.result.PageResult
import com.vless.data.content.result.ProductTypeResult
import com.vless.data.content.service.NewsServiceAware
import com.vless.data.content.service.ProductTypeService
import com.vless.data.content.service.ProductTypeServiceAware
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

@Api("产品类型管理")
@RestController
@RequestMapping("/api/productype")
class ProductTypeController {

    @Autowired
    lateinit var productTypeService: ProductTypeServiceAware

    @ApiIgnore
    @PostMapping("/save")
    fun save(@RequestBody @Valid p: ProductType, result:BindingResult):ProductType{
        return if(result.hasErrors())
            throw BizException("提交信息有误")
        else if(productTypeService.findByLabel(p.label)==null)
            productTypeService.save(p)
        else
            throw BizException("分类名称已经存在")
    }

    @ApiIgnore
    @PostMapping("/update")
    fun update(@RequestBody @Valid p:ProductType, result: BindingResult):ProductType{
        return if(result.hasErrors())
            throw BizException("提交信息有误")
        else if(productTypeService.findByLabel(p.label)!=null&&productTypeService.findByLabel(p.label)!!.id!=p.id)
            throw BizException("分类名称已经存在")
        else
            productTypeService.save(p)
    }

    @ApiIgnore
    @RequestMapping("/remove/{id}",method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id:Long) = productTypeService.delete(id)


    @ApiIgnore
    @GetMapping("/index")
    fun findPage(@ApiIgnore q: ProductTypeQuery):PageResult {
        var page = productTypeService.findPage(q)
        for(pt in page.data as List<ProductType>){
            val listSon:List<ProductType> = productTypeService.findByParent(pt.id)
            pt.children=listSon
        }
        return page
    }

    @ApiOperation("类型列表")
    @ApiImplicitParams(
            ApiImplicitParam(name = "parent", value = "父id,id=0查询一级分类", dataType = "Long", required = false, paramType = "query" )
    )
    @GetMapping("/list")
    fun findType(@ApiIgnore @RequestParam(required = false) parent:Optional<Long>):List<ProductType> {
        return if(parent.isPresent)
            productTypeService.findByParent(parent.get())
        else
            productTypeService.findByParent(0)
    }

    @ApiIgnore
    @GetMapping("/result")
    fun findList():List<ProductTypeResult>{
        val listParent:List<ProductType> = productTypeService.findByParent(0)
        var result:MutableList<ProductTypeResult> = ArrayList()
        for( r in listParent){
            val p=ProductTypeResult()
            p.label=r.label
            p.value=r.id
            val listParentNode:List<ProductType> = productTypeService.findByParent(r.id)
            for(n in listParentNode){
                val c=ProductTypeResult()
                c.label=n.label
                c.value=n.id
                p.children.add(c)
            }
            result.add(p)
        }

        return result
    }

}