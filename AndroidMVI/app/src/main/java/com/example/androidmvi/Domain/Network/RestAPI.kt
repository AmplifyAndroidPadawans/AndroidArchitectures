package com.example.androidmvi.Domain.Network

import com.example.androidmvi.Domain.Entities.TodoTask

interface RestAPI {
    suspend fun getTodoTasks(): List<TodoTask>
}