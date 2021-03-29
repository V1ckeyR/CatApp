package ua.kpi.comsys.ip8418.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8418.databinding.FragmentMovieAddBinding

class MovieAddFragment : Fragment() {
    private var _binding: FragmentMovieAddBinding? = null
    private val binding: FragmentMovieAddBinding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val vm: MovieViewModel by lazy { ViewModelProvider(requireActivity()).get(MovieViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            movieBack.setOnClickListener { (parentFragment as? Container)?.showMovieList() }
            addMovieButton.setOnClickListener {
                var isValid = true

                val title = addMovieTitle.text?.toString()
                if (title.isNullOrBlank()) {
                    isValid = false
                    addMovieTitle.error = "Title should be not empty"
                }

                val year = addMovieYear.text?.toString()
                if (year.isNullOrBlank()) {
                    isValid = false
                    addMovieYear.error = "Year should be not empty"
                }

                val type = addMovieType.text?.toString()
                if (type.isNullOrBlank()) {
                    isValid = false
                    addMovieType.error = "Type should be not empty"
                }

                if (isValid) {
                    vm.movies.add(Movie(title!!, year!!, type!!))
                    (parentFragment as? Container)?.showMovieList()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}