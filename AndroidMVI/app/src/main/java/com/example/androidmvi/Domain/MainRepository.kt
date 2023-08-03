package com.example.androidmvi.Domain

import com.example.androidmvi.Domain.Network.RestAPI

class MainRepository(private val restApi: RestAPI) {

    suspend fun getTodoTasks() = restApi.getTodoTasks()
}