package com.example.androidmvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmvi.Domain.Entities.TodoTask
import com.example.androidmvi.Domain.Network.RestApiImpl
import com.example.androidmvi.Domain.Network.RetrofitBuilder
import com.example.androidmvi.Intent.MainIntent
import com.example.androidmvi.Intent.MainState
import com.example.androidmvi.View.Adapters.MainAdapter
import com.example.androidmvi.View.MainActivityViewModel
import com.example.androidmvi.View.MainViewModelFactory
import com.example.androidmvi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels { MainViewModelFactory(
        RestApiImpl(
            RetrofitBuilder.apiService)
    ) }
    private var mainAdapter = MainAdapter(listOf())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupClicks()
        observeViewModel()

    }

    private fun setupClicks(){
        binding.buttonTasks.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.userIntent.send(MainIntent.FetchTodoTasks("new value"))
            }
        }
    }

    private fun setupUI(){
        binding.recyclerviewTasks.layoutManager = LinearLayoutManager(this)

        binding.recyclerviewTasks.run {
            adapter = mainAdapter
        }
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            mainActivityViewModel.mainState.collect {mainState->
                when(mainState){
                    is MainState.Idle -> binding.progressbar.visibility = View.GONE
                    is MainState.Loading -> binding.progressbar.visibility = View.VISIBLE

                    is MainState.LoadTasks -> {
                        binding.progressbar.visibility = View.GONE
                        renderList(mainState.todoTasks)
                    }

                    is MainState.Error -> {
                        binding.recyclerviewTasks.visibility = View.GONE
                        Toast.makeText(applicationContext, "Error: ${mainState.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderList(listTodoTask: List<TodoTask>){
        mainAdapter.setTodoTasks(listTodoTask)
        mainAdapter.notifyDataSetChanged()
    }

}