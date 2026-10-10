package com.m4ykey.stos.user.repository

import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.core.network.safeApi
import com.m4ykey.stos.user.domain.model.Owner
import com.m4ykey.stos.user.domain.repository.UserRepository
import com.m4ykey.stos.user.mapper.toOwner
import com.m4ykey.stos.user.network.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val service: UserService
) : UserRepository {

    override suspend fun getUserById(userId: Int): Flow<ApiResult<Owner>> {
        return flow {
            val result = safeApi { service.getUserById(id = userId) }

            when (result) {
                is ApiResult.Failure -> {
                    emit(ApiResult.Failure(result.exception))
                }
                is ApiResult.Success -> {
                    val user = result.data.items.map { it.toOwner() }.firstOrNull()
                    if (user != null) {
                        emit(ApiResult.Success(user))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}