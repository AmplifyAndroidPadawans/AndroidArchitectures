package com.example.androidmvi.Domain.Network

import com.example.androidmvi.Domain.Entities.TodoTask

class RestApiImpl(private val restApiService: RestApiService): RestAPI {

    override suspend fun getTodoTasks(): List<TodoTask> {
        return restApiService.listTodo()
    }
}