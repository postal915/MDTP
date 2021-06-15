package com.example.mdtp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mdtp.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val moviesAdapter by lazy { MoviesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getMovies()
        viewModel.movies.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { moviesAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}