package com.example.module9.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.module9.R
import com.example.module9.data.vos.ActorVO
import com.example.module9.data.vos.GenreVO
import com.example.module9.data.vos.MovieVO
import com.example.module9.mvp.presenters.MovieDetailPresenterImpl
import com.example.module9.mvp.presenters.MovieDetailPresenter
import com.example.module9.mvp.views.MovieDetailsView
import com.example.module9.network.utils.IMAGE_BASE_URL
import com.example.module9.viewpod.BestActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailsView {

    companion object{

        private const val EXRTA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context : Context, movieID : Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXRTA_MOVIE_ID, movieID)
            return intent
        }
    }
    // create View Pod
    lateinit var mActorViewPod : BestActorListViewPod
    lateinit var mCreatorViewPod: BestActorListViewPod

    //Presenter
    private lateinit var mPresenter : MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setUpPresenter()

        setUpViewPod()

        val movieID = intent?.getIntExtra(EXRTA_MOVIE_ID,0)
//        Snackbar.make(window.decorView,movieID.toString(), Snackbar.LENGTH_LONG).show()
        movieID?.let {
            mPresenter.onUiReadyInMovieDetail(this,movieID)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProvider(this)[MovieDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    private fun bindData(movie : MovieVO){
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetails)
        tvMovieName.text = movie.title
        tvMovieReleaseYear.text = movie.releaseDate?.substring(0,3)
        tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }
        rbRatingMovieDetails.rating = movie.getRatingBasedOnFiveStars()

        bindGenres(movie, movie.genres ?: listOf())

        tvOverview.text = movie.overview ?: ""
        tvOriginalTitle.text = movie.title ?: ""
        tvType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overview ?: ""
    }

    private fun setUpViewPod() {
        mActorViewPod = vpActors as BestActorListViewPod
        mActorViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            title = getString(R.string.lbl_actor_title),
            moreTitleText = ""
        )

        mCreatorViewPod = vpCreators as BestActorListViewPod
        mCreatorViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            title = getString(R.string.lbl_creator_title),
            moreTitleText = getString(R.string.lbl_creator_more_title)
        )
    }

    private fun bindGenres(
        movie: MovieVO,
        genres: List<GenreVO>
    ){
        movie.genres?.count().let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it != null) {
                if(it < 3){
                    tvThirdGenre.visibility = View.GONE
                }else if(it < 2){
                    tvSecondGenre.visibility = View.GONE
                }
            }
        }

    }

    override fun showMovieDetails(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>) {
        mActorViewPod.setData(cast)
        mCreatorViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showError(errorString: String) {

    }

}