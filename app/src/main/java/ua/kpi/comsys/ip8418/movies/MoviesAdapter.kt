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

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        var poster: ImageView? = null
        var title: TextView? = null
        var year: TextView? = null
        var type: TextView? = null

        init {
            poster = itemView.findViewById(R.id.movie_poster)
            title = itemView.findViewById(R.id.movie_title)
            year = itemView.findViewById(R.id.movie_year)
            type = itemView.findViewById(R.id.movie_type)
        }
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_movie, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val context = holder.itemView.context
        val posterName = movies[position].poster
        if (posterName.isBlank()) {
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
    }
}