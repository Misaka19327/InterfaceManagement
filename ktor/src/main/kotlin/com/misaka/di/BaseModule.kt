package com.misaka.di

import com.misaka.enums.LoggerType
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.async.RedisAsyncCommands
import io.lettuce.core.api.sync.RedisCommands
import org.jetbrains.exposed.sql.Database
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val BaseModule = module {
    LoggerType.entries.forEach { loggerType ->
        single (named(loggerType)) {
            LoggerFactory.getLogger(loggerType.name)
        }
    }

    single<Database> {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "root",
            driver = "org.h2.Driver",
            password = "",
        )
    }

    single<StatefulRedisConnection<String, String>> {
        val client = RedisClient.create("redis://localhost:6379")
        client.connect()
    }

    // 同步api
    single<RedisCommands<String, String>> {
        get<StatefulRedisConnection<String, String>>().sync()
    }

    // 异步api
    single<RedisAsyncCommands<String, String>> {
        get<StatefulRedisConnection<String, String>>().async()
    }
}