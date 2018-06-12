package com.vless.data.content.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import java.util.UUID
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import java.nio.file.Files
import java.nio.file.Paths
import sun.misc.BASE64Encoder


@ApiIgnore
@RestController
class UploadController {

//    @Value("\${file.temp_location}")
//    val TEMP_LOCATION:String=""

    @Value("\${web.upload-path}")
    val LOCATION:String=""

    @PostMapping(value = "/rest/upload")
    fun uploadToRealLocation(@RequestParam("file") file:MultipartFile):UploadResult {
        val suffix:String = file.originalFilename!!.substring(file.originalFilename!!.lastIndexOf(".") + 1)
        val fileName:String = UUID.randomUUID().toString()+"." +suffix
        Files.copy(file.inputStream, Paths.get(LOCATION, fileName))
        return  UploadResult(fileName)
    }

    @PostMapping(value = "/rest/upload64")
    fun uploadToBase64(@RequestParam("file") file:MultipartFile):UploadResult {
        val suffix:String = file.originalFilename!!.substring(file.originalFilename!!.lastIndexOf(".") + 1)
        val fileName:String = UUID.randomUUID().toString()+"." +suffix
        Files.copy(file.inputStream, Paths.get(LOCATION, fileName))
        val encoder = BASE64Encoder()
        val data = encoder.encode(file.bytes)
        return  UploadResult("data:image/png;base64,"+data)
    }

    class UploadResult{
        var result:String=""
        constructor(result:String){
            this.result=result
        }
    }
}