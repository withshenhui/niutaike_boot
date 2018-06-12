package com.vless.data.common

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.Column
import javax.persistence.Temporal
import javax.persistence.TemporalType

open class BaseResult {

    @Column(name = "gmt_create")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var gmtCreate: Date = Date()

    @Column(name = "gmt_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    var gmtModified: Date? = null
}