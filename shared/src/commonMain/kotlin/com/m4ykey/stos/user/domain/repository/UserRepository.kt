package com.m4ykey.stos.user.domain.repository

import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.user.domain.model.Owner
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserById(userId : Int) : Flow<ApiResult<Owner>>

}