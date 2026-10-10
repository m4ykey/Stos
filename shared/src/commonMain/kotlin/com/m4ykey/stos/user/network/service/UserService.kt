package com.m4ykey.stos.user.network.service

import com.m4ykey.stos.core.Items
import com.m4ykey.stos.user.network.model.OwnerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

class UserService(private val client : HttpClient) : RemoteUserService {

    override suspend fun getUserById(
        filter: String,
        site: String,
        id: Int
    ): Items<OwnerDto> {
        return client.get {
            url {
                appendPathSegments("users/$id")
                parameter("filter", filter)
                parameter("site", site)
            }
        }.body()
    }
}