package ua.kpi.comsys.ip8418.movies.data.local

import android.content.Context
import androidx.room.*
import ua.kpi.comsys.ip8418.movies.data.Movie

@Dao
interface MoviesDao {
    @Query("select * from movie where title like '%' || :query || '%'")
    fun getMovies(query: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movies: List<Movie>)
}

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {
        private var instance: MovieDatabase? = null

        private fun buildDB(context: Context): MovieDatabase = Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies_db"
        ).build()

        fun getInstance(context: Context): MovieDatabase {
            return instance ?: buildDB(context).also {
                instance = it
            }
        }
    }
}