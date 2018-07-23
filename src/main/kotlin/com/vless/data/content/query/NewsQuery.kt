package com.vless.data.content.query

import com.vless.data.common.PageQuery
import com.vless.data.content.enum.NewsTypeEnum
import javax.persistence.EnumType
import javax.persistence.Enumerated

class NewsQuery : PageQuery() {

    var title:String? = null

    var showIndex:Boolean? = null

    var type:String? = null
}