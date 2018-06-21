package com.vless.data.content.controller

import com.vless.data.common.BizException
import com.vless.data.content.model.News
import com.vless.data.content.query.NewsQuery
import com.vless.data.content.result.PageResult
import com.vless.data.content.service.NewsServiceAware
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

@Api("新闻管理")
@RestController
@RequestMapping("/api/news")
class NewsController {

    @Autowired
    lateinit var newsService: NewsServiceAware

    @ApiIgnore
    @PostMapping("/save")
    fun save(@RequestBody @Valid news: News, result:BindingResult):News{
        if(news.logo!!.startsWith("http"))
            news.logo=news.logo!!.substring(news.logo!!.lastIndexOf("/")+1,news.logo!!.length)
        news.gmtCreate=Date()
        return if(result.hasErrors())
            throw BizException("提交信息有误")
        else
            newsService.save(news)
    }

    @ApiIgnore
    @PostMapping("/update")
    fun update(@RequestBody @Valid news:News, result: BindingResult):News{
        if(news.logo!!.startsWith("http"))
            news.logo=news.logo!!.substring(news.logo!!.lastIndexOf("/")+1,news.logo!!.length)
        return if(result.hasErrors())
            throw BizException("提交信息有误")
        else
            newsService.save(news)
    }

    @ApiIgnore
    @RequestMapping("/remove/{id}",method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id:Long) = newsService.delete(id)

    @ApiOperation("新闻列表")
    @ApiImplicitParams(
        ApiImplicitParam(name = "title", value = "标题", dataType = "String", required = false, paramType = "query" ),
        ApiImplicitParam(name = "showIndex", value = "首页显示", dataType = "Boolean", required = false, paramType = "query" ),
        ApiImplicitParam(name = "page", value = "页码", dataType = "Long", required = false, paramType = "query" ),
        ApiImplicitParam(name = "limit", value = "每页条数", dataType = "Long", required = false, paramType = "query" )
    )
    @GetMapping("/list")
    fun findPage(@ApiIgnore newsQuery: NewsQuery):PageResult {
        return newsService.findPage(newsQuery)
    }

    @ApiOperation("新闻详情")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long):News = newsService.findById(id)

}