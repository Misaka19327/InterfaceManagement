package com.misaka.di

import com.misaka.HelloService
import org.koin.dsl.module

val HelloModule = module {
    single<HelloService> {
        HelloService {
            println("Hello from HelloService")
        }
    }
}