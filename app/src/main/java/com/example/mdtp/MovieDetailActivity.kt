package com.example.mdtp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mdtp.R
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val id = intent.getStringExtra("id")

        // TODO Remove the rest getStringExtra
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val releaseDate = intent.getStringExtra("release_date")
        textViewID.text = id
        movieTitle_TV.text = title
        movieReleaseDate_TV.text = releaseDate
        movieOverview_TV.text = overview
        //
    }
}