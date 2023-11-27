package com.example.module9.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.module9.R
import com.example.module9.adapters.MovieAdapter
import com.example.module9.data.models.MovieModel
import com.example.module9.data.models.MovieModelImpl
import com.example.module9.delegate.MovieViewHolderDelegate
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

class SearchActivity: AppCompatActivity(), MovieViewHolderDelegate {
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,SearchActivity::class.java)
        }
    }

    //Adapter
    private lateinit var mMovieAdapter: MovieAdapter

    //Models
    private val mMovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpRecyclerView()
        setUpListener()

    }

    private fun setUpRecyclerView(){
        mMovieAdapter = MovieAdapter(this)
        rvSearch.adapter = mMovieAdapter
        rvSearch.layoutManager = GridLayoutManager(this,2)

    }

    private fun setUpListener(){
        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe ({
                mMovieAdapter.setNewData(it)
            },{
                showError(it.localizedMessage  ?: "")
            })
        }


private fun showError(msg : String) {
    Snackbar.make(window.decorView,msg, Snackbar.LENGTH_SHORT).show()
}

    override fun onTapChange(movieID: Int) {

    }
}