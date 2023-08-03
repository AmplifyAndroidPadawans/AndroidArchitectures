package com.example.androidmvvm.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmvvm.Model.Blog

class MainViewModel: ViewModel() {

    var lst = MutableLiveData<ArrayList<Blog>>()
    var newlist = arrayListOf<Blog>()

    fun add(blog: Blog){
        newlist.add(blog)
        lst.value = newlist
    }

    fun remove(blog: Blog){
        newlist.remove(blog)
        lst.value = newlist
    }
}