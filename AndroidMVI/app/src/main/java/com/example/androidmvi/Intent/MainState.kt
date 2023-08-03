package com.example.androidmvi.Intent

import com.example.androidmvi.Domain.Entities.TodoTask

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class LoadTasks(val todoTasks: List<TodoTask>): MainState()
    data class Error(val error: String?): MainState()
}