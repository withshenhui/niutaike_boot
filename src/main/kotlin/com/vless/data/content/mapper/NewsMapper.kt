package com.vless.data.content.mapper

import com.vless.data.content.model.News
import com.vless.data.content.query.NewsQuery
import org.apache.ibatis.annotations.Mapper

@Mapper
interface NewsMapper {

    fun selectAll(actionQuery: NewsQuery):List<News>

    fun selectCount(actionQuery: NewsQuery):Int



}