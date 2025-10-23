package com.m4ykey.stos.core.network

import kmp_stos.composeapp.generated.resources.Res
import kmp_stos.composeapp.generated.resources.failed_to_process_response
import kmp_stos.composeapp.generated.resources.no_internet_connection
import kmp_stos.composeapp.generated.resources.request_error
import kmp_stos.composeapp.generated.resources.request_timed_out
import kmp_stos.composeapp.generated.resources.server_problem
import kmp_stos.composeapp.generated.resources.too_many_requests
import kmp_stos.composeapp.generated.resources.unauthorized
import kmp_stos.composeapp.generated.resources.unknown_error
import org.jetbrains.compose.resources.StringResource

sealed class ApiException(
    val error : StringResource,
    val statusCode : Int? = null
) : Exception("Unresolved string") {

    class NoConnection(
        error : StringResource = Res.string.no_internet_connection
    ) : ApiException(error)

    class RequestTimeout(
        error : StringResource = Res.string.request_timed_out
    ) : ApiException(error)

    class ServerProblem(
        code : Int,
        error : StringResource = Res.string.server_problem
    ) : ApiException(error, code)

    class ClientError(
        code : Int,
        error : StringResource = Res.string.request_error
    ) : ApiException(error, code)

    class ParseError(
        error : StringResource = Res.string.failed_to_process_response
    ) : ApiException(error)

    class TooManyRequests(
        error : StringResource = Res.string.too_many_requests
    ) : ApiException(error, 429)

    class Unauthorized(
        error : StringResource = Res.string.unauthorized
    ) : ApiException(error, 401)

    class UnknownError(
        error : StringResource = Res.string.unknown_error
    ) : ApiException(error)

}