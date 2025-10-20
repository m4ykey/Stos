package com.m4ykey.stos.core.network

sealed class ApiException(
    val error : String,
    val statusCode : Int? = null
) : Exception("Unresolved string") {

    class NoConnection(
        error : String = "No internet connection"
    ) : ApiException(error)

    class RequestTimeout(
        error : String = "Request timed out"
    ) : ApiException(error)

    class ServerProblem(
        code : Int,
        error : String = "Server problem"
    ) : ApiException(error, code)

    class ClientError(
        code : Int,
        error : String = "Request error"
    ) : ApiException(error, code)

    class ParseError(
        error : String = "Failed to process response"
    ) : ApiException(error)

    class TooManyRequests(
        error : String = "Too many requests"
    ) : ApiException(error, 429)

    class Unauthorized(
        error : String = "Unauthorized"
    ) : ApiException(error, 401)

    class UnknownError(
        error : String = "Unknown error"
    ) : ApiException(error)

}