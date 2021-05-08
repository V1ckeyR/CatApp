package ua.kpi.comsys.ip8418.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8418.R
import ua.kpi.comsys.ip8418.databinding.FragmentMoviesBinding
import java.util.*

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalSerializationApi
    private val vm by lazy { ViewModelProvider(requireActivity()).get(MovieViewModel::class.java) }

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchMovie.setText(vm.searchText)

        val moviesAdapter = MoviesAdapter(vm.movies.value ?: listOf())

        with(binding.movies) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        moviesAdapter.setOnItemClicked {
            val id = it.imdbID

            if (id.isNullOrEmpty()) {
                vm.movieInfo.value = MovieInfo(it.title, it.year, it.poster)
            } else {
                vm.loadMovieInfo(id)
            }
        }

        with(binding) {
            searchMovie.addTextChangedListener {
                vm.searchText = it.toString()

                if ((it?.count() ?: 0) >= 3) {
                    vm.loadMovies(it.toString())
                } else {
                    moviesAdapter.update(listOf())
                    movies.isVisible = false
                    noMovie.isVisible = true
                    noMovie.text = getString(R.string.keep_typing)
                }
            }

            vm.movies.observe(viewLifecycleOwner) {
                moviesAdapter.update(it)
                noMovie.isVisible = it.isNullOrEmpty()
                movies.isVisible = !it.isNullOrEmpty()
            }

            vm.error.observe(viewLifecycleOwner) {
                noMovie.isVisible = true
                noMovie.text = it
            }

            vm.movieInfo.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                (parentFragment as? Container)?.showMovieInfo()
            }

            vm.MIError.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}