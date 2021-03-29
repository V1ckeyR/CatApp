package ua.kpi.comsys.ip8418.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8418.databinding.FragmentMoviesBinding
import java.io.IOException
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

    private val vm by lazy { ViewModelProvider(requireActivity()).get(MovieViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.movies.isEmpty()) {
            val jsonString = getJsonDataFromAsset(requireContext(), "MoviesList.txt")
            val search = Json.decodeFromString<Search>(jsonString!!)
            vm.movies = search.movies.toMutableList()
        }

        val moviesAdapter = MoviesAdapter(vm.movies)

        with(binding.movies) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        moviesAdapter.setOnItemClicked {
            val inString = getJsonDataFromAsset(requireContext(), "Movies/${it.imdbID}.txt")
            val movieInfo = if (inString == null) {
                MovieInfo(it.title, it.year, it.poster)
            } else {
                Json { ignoreUnknownKeys = true }.decodeFromString(inString)
            }
            (parentFragment as? Container)?.showMovieInfo(movieInfo)
        }

        moviesAdapter.setOnItemDeleted {
            vm.movies.remove(it)
            binding.noMovie.isVisible = vm.movies.isNullOrEmpty()
            binding.movies.isVisible = vm.movies.isNotEmpty()

            val query = binding.searchMovie.text?.toString() ?: ""
            if (query.isNotBlank()) {
                val n = vm.movies.filter { movie ->
                    movie.title
                            .toLowerCase(Locale.ROOT)
                            .contains(query.toLowerCase(Locale.ROOT))
                }.size

                binding.noMovie.isVisible = n == 0
                binding.movies.isVisible = n != 0
            }
        }

        with(binding) {
            searchMovie.addTextChangedListener {
                val m = vm.movies.filter { movie ->
                    movie.title
                            .toLowerCase(Locale.ROOT)
                            .contains(it?.toString()?.toLowerCase(Locale.ROOT) ?: "")
                }
                moviesAdapter.update(m.toMutableList())
                noMovie.isVisible = m.isNullOrEmpty()
                movies.isVisible = !m.isNullOrEmpty()
            }
            addMovie.setOnClickListener { (parentFragment as? Container)?.showMovieAdd() }
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}