package com.vless.data.content.mapper

import com.vless.data.content.model.News
import com.vless.data.content.query.NewsQuery
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface NewsMapper {

    fun selectAll(actionQuery: NewsQuery):List<News>

    fun selectCount(actionQuery: NewsQuery):Int


    @Select("SELECT * FROM ntk_news WHERE id > #{id} ORDER BY id ASC LIMIT 1")
    fun selectNext(@Param("id") id:Long):News?

    @Select("SELECT * FROM ntk_news WHERE id < #{id} ORDER BY id DESC LIMIT 1")
    fun selectPrev(@Param("id") id:Long):News?

}