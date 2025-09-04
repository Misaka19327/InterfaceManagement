package com.misaka.plugins

import com.ucasoft.ktor.simpleCache.SimpleCache
import com.ucasoft.ktor.simpleCache.cacheOutput
import com.ucasoft.ktor.simpleMemoryCache.memoryCache
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

fun Application.configureSimpleCache() {
    install(SimpleCache) {
        memoryCache {
            invalidateAt = 5.seconds
        }
    }

    // for custom cache expiredAt example
//    routing {
//        cacheOutput(2.seconds) {
//            get("/simpleCache") {
//                call.respond(Random.nextInt())
//            }
//        }
//    }
}