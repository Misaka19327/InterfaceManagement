package com.misaka.plugins

import io.github.flaxoos.ktor.server.plugins.taskscheduling.TaskScheduling
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.database.DefaultTaskLockTable
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.database.jdbc
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.redis.redis
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Application.configureTask() {
    install(TaskScheduling) {
        redis { // 任务调度所需redis
            name = "DefaultManager"
            connectionPoolInitialSize = 2
            host = "host"
            port = 6379
            username = "my_username"
            password = "my_password"
            connectionAcquisitionTimeoutMs = 1_000
            lockExpirationMs = 60_000
        }

        val globalDatabase: Database by inject()
        jdbc("my jdbc manager") { // 任务调度所需数据库
            name = "DefaultManager"
            database = globalDatabase.also {
                transaction { SchemaUtils.create(DefaultTaskLockTable) }
            }
        }

        // task example
//        task {
//            name = "My task"
//            task = { taskExecutionTime ->
//                log.info("My task is running: $taskExecutionTime")
//            }
//            kronSchedule = {
//                hours {
//                    from(0).every(12)
//                }
//                minutes {
//                    from(10).every(30)
//                }
//            }
//            concurrency = 2
//        }
    }
}