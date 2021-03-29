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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            movieTitle.text = vm.movieInfo?.title ?: getString(R.string.noData)
            movieYear.text = vm.movieInfo?.year ?: getString(R.string.noData)
            movieRated.text = vm.movieInfo?.rated ?: getString(R.string.noData)
            movieReleased.text = vm.movieInfo?.released ?: getString(R.string.noData)
            movieRuntime.text = vm.movieInfo?.runtime ?: getString(R.string.noData)
            movieGenre.text = vm.movieInfo?.genre ?: getString(R.string.noData)
            movieDirector.text = vm.movieInfo?.director ?: getString(R.string.noData)
            movieActors.text = vm.movieInfo?.rated ?: getString(R.string.noData)
            moviePlot.text = vm.movieInfo?.plot ?: getString(R.string.noData)
            movieLanguage.text = vm.movieInfo?.language ?: getString(R.string.noData)
            movieCountry.text = vm.movieInfo?.country ?: getString(R.string.noData)
            movieAwards.text = vm.movieInfo?.awards ?: getString(R.string.noData)
            movieRating.text = vm.movieInfo?.rating ?: getString(R.string.noData)

            val context = requireContext()
            if (vm.movieInfo?.poster.isNullOrBlank()) {
                moviePoster.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_movies))
                moviePoster.setBackgroundColor(Color.WHITE)
            }
            else {
                val inputStream = context.assets.open("Posters/${vm.movieInfo?.poster}")
                moviePoster.setImageDrawable(Drawable.createFromStream(inputStream, null))
                inputStream.close()
            }

            movieBack.setOnClickListener { (parentFragment as? Container)?.showMovieList() }
            (requireActivity() as MainActivity).setOnBack { (parentFragment as? Container)?.showMovieList() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}