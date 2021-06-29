package com.example.mdtp.presentation.search

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mdtp.R
import com.example.mdtp.presentation.adapter.MoviesAdapter
import com.example.mdtp.repository.Repository
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var viewModel: SearchViewModel

    private val moviesAdapter by lazy { MoviesAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        viewModel.foundFilms.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    moviesAdapter.setData(it.results)
                }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query.isNotEmpty()) {
            searchSite(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText.isNotEmpty()) {
            searchSite(newText)
        }
        return true
    }

    private fun searchSite(query: String) {
        viewModel.getSearchMovie(query)
    }

    private fun setupRecyclerView() {
        recyclerViewSearch.adapter = moviesAdapter
        recyclerViewSearch.layoutManager = GridLayoutManager(this, 3)
    }
}