package com.example.mdtp.presentation.search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mdtp.R
import com.example.mdtp.repository.Repository

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val repository = Repository()
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        viewModel.getSearchMovie("sex")
        viewModel.foundFilms.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let {

                }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_LONG).show()
            }
        })
    }
}