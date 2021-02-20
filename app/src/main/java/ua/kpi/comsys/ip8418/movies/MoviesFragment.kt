package ua.kpi.comsys.ip8418.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8418.databinding.FragmentMoviesBinding
import java.io.IOException

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = getJsonDataFromAsset(requireContext(), "MoviesList.txt")
        val search = Json.decodeFromString<Search>(jsonString!!)
        with(binding.movies) {
            adapter = MoviesAdapter(search.movies)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                    DividerItemDecoration(
                            context,
                            (layoutManager as LinearLayoutManager).orientation
                    )
            )
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