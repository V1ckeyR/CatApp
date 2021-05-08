package ua.kpi.comsys.ip8418.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8418.MainActivity
import ua.kpi.comsys.ip8418.R
import ua.kpi.comsys.ip8418.databinding.FragmentContainerBinding

class Container : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMovieList()
    }

    private fun changeFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
                .replace(R.id.container_movie, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun showMovieList() {
        changeFragment(MoviesFragment())
    }

    fun showMovieInfo() {
        changeFragment(MovieInfoFragment())
    }
}