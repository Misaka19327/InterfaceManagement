package com.misaka.plugins

import com.misaka.di.BaseModule
import com.misaka.di.HelloModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        modules(
            HelloModule,
            BaseModule
        )
    }
}