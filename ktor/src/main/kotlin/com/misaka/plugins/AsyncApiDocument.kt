package com.misaka.plugins

import com.asyncapi.kotlinasyncapi.context.service.AsyncApiExtension
import com.asyncapi.kotlinasyncapi.ktor.AsyncApiPlugin
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureAsyncApiDocument() {
    install(AsyncApiPlugin) {
        extension = AsyncApiExtension.builder {
            info {
                title("Sample API")
                version("1.0.0")
            }
        }
    }
}