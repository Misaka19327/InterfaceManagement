package com.misaka.plugins

import com.misaka.enums.LoggerType
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.request.path
import org.koin.core.qualifier.named
import org.koin.ktor.ext.inject
import org.slf4j.Logger
import org.slf4j.event.Level

fun Application.configureCallLogging() {
    val callLogger by inject<Logger>(named(LoggerType.CallLogger))
    install(CallLogging) {
        logger = callLogger
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}