package ua.kpi.comsys.ip8418.movies

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8418.MainActivity
import ua.kpi.comsys.ip8418.R
import ua.kpi.comsys.ip8418.databinding.FragmentMovieInfoBinding

class MovieInfoFragment : Fragment() {
    private var _binding: FragmentMovieInfoBinding? = null
    private val binding: FragmentMovieInfoBinding get() = _binding!!

    private val vm by lazy { ViewModelProvider(requireActivity()).get(MovieViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val movieInfo = vm.movieInfo.value ?: error("Something wrong with MovieInfo")
            movieTitle.text = movieInfo.title ?: getString(R.string.noData)
            movieYear.text = movieInfo.year ?: getString(R.string.noData)
            movieRated.text = movieInfo.rated ?: getString(R.string.noData)
            movieReleased.text = movieInfo.released ?: getString(R.string.noData)
            movieRuntime.text = movieInfo.runtime ?: getString(R.string.noData)
            movieGenre.text = movieInfo.genre ?: getString(R.string.noData)
            movieDirector.text = movieInfo.director ?: getString(R.string.noData)
            movieActors.text = movieInfo.rated ?: getString(R.string.noData)
            moviePlot.text = movieInfo.plot ?: getString(R.string.noData)
            movieLanguage.text = movieInfo.language ?: getString(R.string.noData)
            movieCountry.text = movieInfo.country ?: getString(R.string.noData)
            movieAwards.text = movieInfo.awards ?: getString(R.string.noData)
            movieRating.text = movieInfo.rating ?: getString(R.string.noData)

            val context = requireContext()
            if (movieInfo.poster.isNullOrBlank()) {
                moviePoster.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_movies))
                moviePoster.setBackgroundColor(Color.WHITE)
            } else Picasso.get()
                    .load(movieInfo.poster)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_movies)
                    .into(moviePoster)

            movieBack.setOnClickListener {
                vm.movieInfo.value = null
                (parentFragment as? Container)?.showMovieList()
            }

            (requireActivity() as MainActivity).setOnBack {
                vm.movieInfo.value = null
                (parentFragment as? Container)?.showMovieList()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}