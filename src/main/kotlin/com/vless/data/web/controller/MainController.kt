package com.vless.data.content.controller

import com.vless.data.common.BizException
import com.vless.data.content.query.NewsQuery
import com.vless.data.content.query.ProductIndexQuery
import com.vless.data.content.query.ProductQuery
import com.vless.data.content.result.ProductIndexResult
import com.vless.data.content.result.ProductResult
import com.vless.data.content.service.NewsServiceAware
import com.vless.data.content.service.ProductServiceAware
import com.vless.data.content.service.ProductTypeService
import com.vless.data.content.service.ProductTypeServiceAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class MainController {

    @Autowired
    lateinit var productTypeService: ProductTypeServiceAware

    @Autowired
    lateinit var newsService: NewsServiceAware

    @Autowired
    lateinit var productService: ProductServiceAware

    @ModelAttribute
    fun init(model: Model){
        val listType=productTypeService.findByParent(0)
        model.addAttribute("types",listType)
        var newsQuery=NewsQuery()
        newsQuery.limit=100
        newsQuery.showIndex=true
        val listNews=newsService.findPage(newsQuery)
        var productQuery=ProductQuery()
        productQuery.limit=8
        val page=productService.findPage(productQuery)
        model.addAttribute("news",listNews)
        model.addAttribute("news1",listNews.data!!.get(0))
        model.addAttribute("products",page.data)

        var newsQueryLeft=NewsQuery()
        newsQueryLeft.limit=4
        newsQueryLeft.showIndex=true
        model.addAttribute("newsLeft",newsService.findPage(newsQueryLeft))
        var newsQueryRight=NewsQuery()
        newsQueryRight.limit=4
        newsQueryRight.page=2
        newsQueryRight.showIndex=true
        model.addAttribute("newsRight",newsService.findPage(newsQueryRight))
    }

    @GetMapping("/")
    fun index(model: Model): String{

        return "index"
    }

    @GetMapping("/sitemap")
    fun sitemap(model: Model): String{

        return "sitemap"
    }

    @GetMapping("/category/{id}")
    fun category(@PathVariable id:Long,model: Model): String{
        if(id<=0)
            throw BizException("productTypeParentId不能<=0")
        var productQuery: ProductIndexQuery= ProductIndexQuery()
        productQuery.productTypeId=id
        var result= ProductIndexResult()
        result.productTypeParentId=productQuery.productTypeId
        var list:MutableList<ProductIndexResult.ProductNextCat> = ArrayList()
        val list1=productTypeService.findByParent(productQuery.productTypeId)
        for(pt in list1){
            var cat= ProductIndexResult.ProductNextCat()
            cat.productTypeId=pt.id
            cat.productTypeName=pt.label
            productQuery.productTypeId=pt.id
            val list2=productService.findList(productQuery)
            var listPR:MutableList<ProductResult> = ArrayList()
            for(pt2 in list2){
                listPR.add(pt2)
            }
            cat.list=listPR
            list.add(cat)
        }
        result.list=list
        model.addAttribute("catProducts",result)

        val listType=productTypeService.findByParent(id)
        model.addAttribute("catTypes",listType)
        model.addAttribute("type",id)
        return "category"
    }

    @GetMapping("/product/{id}")
    fun productDetail(@PathVariable id:Long,model: Model): String{
        val product=productService.findById(id)
        model.addAttribute("product",product)
        val listType=productTypeService.findByParent(productTypeService.findById(product.productTypeId).parent)
        model.addAttribute("catTypes",listType)
        model.addAttribute("rlProducts",productService.findByProductTypeId(product.productTypeId))
        return "detail"
    }

    @GetMapping("/footer")
    fun footer(model: Model): String{
        var newsQuery=NewsQuery()
        newsQuery.limit=100
        val listNews=newsService.findPage(newsQuery)
        model.addAttribute("news",listNews)
        return "footer"
    }

    @GetMapping("/about")
    fun about(model: Model): String{
        return "about"
    }

    @GetMapping("/contact")
    fun contact(model: Model): String{
        return "contact"
    }

    @GetMapping("/news")
    fun news(model: Model,newsQuery: NewsQuery): String{
        val page=newsService.findPage(newsQuery)
        model.addAttribute("newsList",page)
        model.addAttribute("pageNum",newsQuery.page)
        if(page.count!!/newsQuery!!.limit>1)
            model.addAttribute("totalPages",page.count!!/newsQuery!!.limit)
        else
            model.addAttribute("totalPages",1)
        model.addAttribute("totalElements",page.count)
        var productQuery=ProductQuery()
        productQuery.limit=4
        model.addAttribute("rlProducts",productService.findPage(productQuery).data)
        return "newsList"
    }

    @GetMapping("/news/{id}")
    fun newsDetail(@PathVariable id:Long,model: Model): String{
        model.addAttribute("newsDetail",newsService.findById(id))
        model.addAttribute("nextNews",newsService.selectNext(id))
        model.addAttribute("prevNews",newsService.selectPrev(id))
        var productQuery=ProductQuery()
        productQuery.limit=4
        val page=productService.findPage(productQuery)
        model.addAttribute("rlProducts",page.data)
        return "news"
    }

    @GetMapping("/search")
    fun product(model: Model,productQuery: ProductQuery): String{
        productQuery.limit=1000
        val page=productService.findPage(productQuery)
        model.addAttribute("searchList",page)
        model.addAttribute("pageNum",productQuery.page)
        if(page.count!!/productQuery!!.limit>1)
            model.addAttribute("totalPages",page.count!!/productQuery!!.limit)
        else
            model.addAttribute("totalPages",1)
        model.addAttribute("totalElements",page.count)
        return "search"
    }

}