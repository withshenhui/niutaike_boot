
package com.vless.data

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement


@SpringBootApplication
@EnableTransactionManagement
//@MapperScan(basePackages = arrayOf("com.vless.data"))
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
