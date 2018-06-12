package com.vless.data.security.repository

import com.vless.data.security.model.SecUser
import com.vless.data.security.result.SecUserResult
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SecUserRepository : CrudRepository<SecUser,Long> {

}