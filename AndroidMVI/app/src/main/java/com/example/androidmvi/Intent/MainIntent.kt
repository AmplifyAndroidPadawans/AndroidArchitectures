package com.example.androidmvi.Intent

sealed class MainIntent {
    class FetchTodoTasks(val dataString: String): MainIntent()
}

