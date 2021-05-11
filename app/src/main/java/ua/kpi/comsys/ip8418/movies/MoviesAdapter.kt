package ua.kpi.comsys.ip8418.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kpi.comsys.ip8418.R
import ua.kpi.comsys.ip8418.movies.data.Movie

class MoviesAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {
    private var onItemClicked: (Movie) -> Unit = {}

    fun setOnItemClicked(f: (Movie) -> Unit) {
        onItemClicked = f
    }

    fun update(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var poster: ImageView? = itemView.findViewById(R.id.movie_poster)
        var title: TextView? = itemView.findViewById(R.id.movie_title)
        var year: TextView? = itemView.findViewById(R.id.movie_year)
        var type: TextView? = itemView.findViewById(R.id.movie_type)
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_movie, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        Picasso.get()
                .load(movies[position].poster)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_movies)
                .into(holder.poster)


        holder.title?.text = movies[position].title
        holder.year?.text = movies[position].year
        holder.type?.text = movies[position].type

        val movie = movies[position]

        holder.itemView.setOnClickListener {
            onItemClicked(movie)
        }
    }
}