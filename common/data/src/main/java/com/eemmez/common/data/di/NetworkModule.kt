package com.eemmez.common.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /*
    @Provides
    @Singleton
    fun createKtorClient(): HttpClient =
        HttpClient(Android).config {
            defaultRequest {
                url("http://localhost:8080/")
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }
        }
     */
}