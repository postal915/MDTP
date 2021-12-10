package com.example.mdtp.presentation.search

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mdtp.R
import com.example.mdtp.presentation.adapter.MoviesPagingAdapter
import com.example.mdtp.repository.Repository
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var viewModel: SearchViewModel

    private val moviesAdapter by lazy { MoviesPagingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

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
            startSearch(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText.isNotEmpty()) {
            startSearch(newText)
        }
        return true
    }

    private fun startSearch(searchQuery: String) {
        lifecycleScope.launch {
            viewModel.getMoviesPagingFlow(searchQuery)
                .collectLatest { it ->
                    moviesAdapter.submitData(it)
                }
        }
    }

    private fun setupRecyclerView() {
        recyclerViewSearch.adapter = moviesAdapter
        recyclerViewSearch.layoutManager = GridLayoutManager(this, 3)
    }
}