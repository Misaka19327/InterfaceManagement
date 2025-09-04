package com.misaka

import com.misaka.plugins.configureAsyncApiDocument
import com.misaka.plugins.configureAuthenticationJwt
import com.misaka.plugins.configureCallLogging
import com.misaka.plugins.configureCors
import com.misaka.plugins.configureKoin
import com.misaka.plugins.configureSSE
import com.misaka.plugins.configureSimpleCache
import com.misaka.plugins.configureTask
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureKoin() // 必须先生成依赖

    configureAsyncApiDocument()
    configureAuthenticationJwt()
    configureCallLogging()
    configureCors()
    configureSimpleCache()
    configureSSE()
    configureTask()
}
