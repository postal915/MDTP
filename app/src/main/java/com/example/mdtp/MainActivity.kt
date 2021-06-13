package com.example.mdtp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mdtp.stevdza.MainViewModel
import com.example.mdtp.stevdza.MainViewModelFactory
import com.example.mdtp.stevdza.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.page.toString())
                Log.d("Response", response.body()?.results.toString())
                Log.d("Response", response.body()?.totalPages.toString())
                Log.d("Response", response.body()?.totalResults.toString())
            } else {
                Log.d("Response error", response.errorBody().toString())
                Log.d("Response code", response.code().toString())
            }
        })
    }
}