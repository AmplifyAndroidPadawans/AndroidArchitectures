package com.example.androidmvvm.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmvvm.Model.Blog
import com.example.androidmvvm.R
import com.example.androidmvvm.ViewModel.MainViewModel
import com.example.androidmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.button.setOnClickListener {
            addData()
        }

        initialiseAdapter()
    }

    private fun initialiseAdapter(){
        binding.recycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.lst.observe(this) {
            Log.i("data", it.toString())
            binding.recycler.adapter = NoteRecyclerAdapter(viewModel, it, this)
        }
    }

    private fun addData(){
        val title = binding.titletxt.text.toString()
        if(title.isBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            val blog = Blog(title)
            viewModel.add(blog)
            binding.titletxt.text.clear()
            binding.recycler.adapter?.notifyDataSetChanged()
        }

    }
}