package com.m4ykey.stos.network

import com.m4ykey.stos.shared.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {

    private const val BASE_URL = "api.stackexchange.com"
    private const val API_VERSION = "2.3"
    private const val TIMEOUT = 15_000L

    fun create(
        enableLogging : Boolean = false,
        engine : HttpClientEngine = CIO.create()
    ) : HttpClient = HttpClient(engine) {

        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }

        if (enableLogging) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO

                sanitizeHeader { header ->
                    header == HttpHeaders.Authorization
                }
            }
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL

                if (pathSegments.isEmpty() || pathSegments.first() != API_VERSION) {
                    path(API_VERSION, "")
                }
                parameters.append("key", BuildKonfig.API_KEY)
            }
            contentType(ContentType.Application.Json)
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }

        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
        }
    }

}