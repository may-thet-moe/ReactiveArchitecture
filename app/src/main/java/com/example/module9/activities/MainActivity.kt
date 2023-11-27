package com.example.module9.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module9.R
import com.example.module9.adapters.BannerAdapter
import com.example.module9.adapters.ShowCaseMovieAdapter
import com.example.module9.data.vos.ActorVO
import com.example.module9.data.vos.GenreVO
import com.example.module9.data.vos.MovieVO
import com.example.module9.mvp.presenters.MainPresenter
import com.example.module9.mvp.presenters.MainPresenterImpl
import com.example.module9.mvp.views.MainView
import com.example.module9.viewpod.BestActorListViewPod
import com.example.module9.viewpod.MovieListViewPod
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mBannerAdapter : BannerAdapter
    lateinit var mShowCaseAdapter : ShowCaseMovieAdapter

    lateinit var mBestPopularMovieViewPod : MovieListViewPod
    lateinit var mMovieByGenreViewPod : MovieListViewPod
    lateinit var mActorListViewPod : BestActorListViewPod

    //Presenter
    private lateinit var mPresenter : MainPresenter

//    //Model
//    private val mMovieModel : MovieModel = MovieModelImpl
//
//    //Data
//    private var mGenres: List<GenreVO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        setUpMovieViewPod()
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()
        setUPListener()

        mPresenter.onUiReady(this)

    }


    private fun setUpPresenter(){
        mPresenter  = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }



    fun setUpMovieViewPod(){
        mBestPopularMovieViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieViewPod.setUpMovieListViewPod(mPresenter)

        mMovieByGenreViewPod = vpMovieByGenreList as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = vpBestActor as BestActorListViewPod
    }

    private fun setUPListener() {

        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position?:0)
                }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
//               Snackbar.make(window.decorView,tab?.text ?: "",Snackbar.LENGTH_LONG).show()
            }



        )

        ivSearch.setOnClickListener {
            startActivity(SearchActivity.newIntent(this))
        }
    }

    private fun setUpShowCaseRecyclerView(){
        mShowCaseAdapter = ShowCaseMovieAdapter(mPresenter)
        rvShowCasesMovies.adapter = mShowCaseAdapter
        rvShowCasesMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {

        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }

    }

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMoves: List<MovieVO>) {
        mBestPopularMovieViewPod.setNewData(popularMoves)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowCaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTabLayout (genreList)
    }

    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mMovieByGenreViewPod.setNewData(moviesByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieID: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieID = movieID))
    }

    override fun navigateToMovieDetailsScreenTran(
        movieID: Int,
        transitionPair: Pair<View, String>
    ) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,transitionPair
        )
        startActivity(MovieDetailActivity.newIntent(this, movieID = movieID), options.toBundle())
    }

    override fun showError(errorString: String) {

    }


    private fun setUpBannerViewPager(){
        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover,menu)
        return true
    }

}