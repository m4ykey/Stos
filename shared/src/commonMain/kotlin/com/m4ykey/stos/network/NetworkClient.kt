package com.m4ykey.stos.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {

    private const val API_VERSION = "2.3"
    private const val BASE_URL = "api.stackexchange.com"

    fun create(engine: HttpClientEngine) : HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json (
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }

            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 3)
                exponentialDelay()
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 15_000
                socketTimeoutMillis = 15_000
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL

                    if (pathSegments.isEmpty() || pathSegments.first() != API_VERSION) {
                        path(API_VERSION, "")
                    }

                    url.parameters.append("key", "")
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

}