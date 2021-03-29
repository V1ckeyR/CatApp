package ua.kpi.comsys.ip8418.movies

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8418.R

class MoviesAdapter(private var movies: MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {
    private var onItemClicked: (Movie) -> Unit = {}
    private var onItemDeleted: (Movie) -> Unit = {}

    fun setOnItemClicked(f: (Movie) -> Unit) {
        onItemClicked = f
    }

    fun setOnItemDeleted(f: (Movie) -> Unit) {
        onItemDeleted = f
    }

    fun update(updatedMovies: MutableList<Movie>) {
        movies = updatedMovies
        notifyDataSetChanged()
    }

    class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var poster: ImageView? = itemView.findViewById(R.id.movie_poster)
        var title: TextView? = itemView.findViewById(R.id.movie_title)
        var year: TextView? = itemView.findViewById(R.id.movie_year)
        var type: TextView? = itemView.findViewById(R.id.movie_type)
        var remove: ImageView? = itemView.findViewById(R.id.movie_remove)
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_movie, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val context = holder.itemView.context
        val posterName = movies[position].poster
        if (posterName.isNullOrBlank()) {
            holder.poster?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_movies))
            holder.poster?.setBackgroundColor(Color.WHITE)
        }
        else {
            val inputStream = context.assets.open("Posters/$posterName")
            holder.poster?.setImageDrawable(Drawable.createFromStream(inputStream, null))
            inputStream.close()
        }

        holder.title?.text = movies[position].title
        holder.year?.text = movies[position].year
        holder.type?.text = movies[position].type

        val movie = movies[position]

        holder.itemView.setOnClickListener {
            onItemClicked(movie)
        }

        holder.remove?.setOnClickListener {
            val pos = movies.indexOf(movie)
            movies.remove(movie)
            notifyItemRemoved(pos)
            onItemDeleted(movie)
        }
    }
}