package com.misaka.routes

import io.github.flaxoos.ktor.server.plugins.ratelimiter.RateLimiting
import io.github.flaxoos.ktor.server.plugins.ratelimiter.implementations.TokenBucket
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlin.time.Duration.Companion.seconds

fun Application.loadRoutes() {
    routing {
        route("/") {
            install(RateLimiting) {
                rateLimiter {
                    type = TokenBucket::class
                    capacity = 1000
                    rate = 1.seconds
                }
            }
        }
    }
}