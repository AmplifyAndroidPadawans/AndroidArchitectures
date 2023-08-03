package com.example.androidmvi.Domain.Network

import com.example.androidmvi.Domain.Entities.TodoTask
import retrofit2.http.GET

interface RestApiService {
    @GET("todos")
    suspend fun listTodo(): List<TodoTask>
}