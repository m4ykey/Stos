package com.m4ykey.stos.user.network.service

import com.m4ykey.stos.core.Filters.USER_FILTER
import com.m4ykey.stos.core.Items
import com.m4ykey.stos.user.network.model.OwnerDto

interface RemoteUserService {

    suspend fun getUserById(
        filter : String = USER_FILTER,
        site : String = "stackoverflow",
        id : Int
    ) : Items<OwnerDto>

}