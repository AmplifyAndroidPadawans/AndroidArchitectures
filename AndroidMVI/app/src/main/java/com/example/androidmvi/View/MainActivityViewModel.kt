package com.example.androidmvi.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvi.Domain.MainRepository
import com.example.androidmvi.Intent.MainIntent
import com.example.androidmvi.Intent.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    val mainState: StateFlow<MainState>
        get() = _mainState

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is MainIntent.FetchTodoTasks -> fetchTodoTasks(it.dataString)
                }
            }
        }
    }

    private suspend fun fetchTodoTasks(dataString: String){
        viewModelScope.launch {
            _mainState.value = MainState.Loading

            _mainState.value = try{
                MainState.LoadTasks(repository.getTodoTasks())
            }catch (e: Exception){
                MainState.Error(e.message)
            }
        }
    }
}