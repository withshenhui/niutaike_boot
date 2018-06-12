package com.vless.data.content.service

import com.vless.data.content.model.News
import com.vless.data.content.query.NewsQuery
import com.vless.data.content.result.PageResult

interface NewsServiceAware  {

    fun save(news:News):News

    fun delete(id:Long)

    fun findPage(actionQuery: NewsQuery):PageResult

    fun findById(id:Long) : News

}