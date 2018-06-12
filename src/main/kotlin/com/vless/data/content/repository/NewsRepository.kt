package com.vless.data.content.repository

import com.vless.data.content.model.News
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository : CrudRepository<News,Long> {

}