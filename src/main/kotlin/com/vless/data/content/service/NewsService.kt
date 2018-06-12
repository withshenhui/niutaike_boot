package com.vless.data.content.service

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.vless.data.content.repository.NewsRepository
import com.vless.data.content.mapper.NewsMapper
import com.vless.data.content.model.News
import com.vless.data.content.query.NewsQuery
import com.vless.data.content.result.PageResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NewsService : NewsServiceAware {

    @Autowired
    lateinit var newsRepository: NewsRepository

    @Autowired
    lateinit var newsMapper: NewsMapper


    override fun save(news: News): News = newsRepository.save(news)

    override fun delete(id: Long) = newsRepository.deleteById(id)

    override fun findPage(newsQuery: NewsQuery): PageResult {
        PageHelper.startPage<News>(newsQuery.page,newsQuery.limit)
        val list:List<News> = newsMapper.selectAll(newsQuery)
        val pageInfo:PageInfo<News> = PageInfo(list)
        return PageResult(pageInfo.total,list)
    }

    override fun findById(id: Long): News = newsRepository.findById(id).get()


}