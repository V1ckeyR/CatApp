package ua.kpi.comsys.ip8418.images.data.local

import android.content.Context
import androidx.room.*
import ua.kpi.comsys.ip8418.images.data.Image

@Dao
interface ImagesDao {
    @Query("select * from image")
    fun getImages(): List<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(images: List<Image>)
}

@Database(entities = [Image::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao

    companion object {
        private var instance: ImageDatabase? = null

        private fun buildDB(context: Context): ImageDatabase = Room.databaseBuilder(
            context,
            ImageDatabase::class.java,
            "images_db"
        ).build()

        fun getInstance(context: Context): ImageDatabase {
            return instance ?: buildDB(context).also {
                instance = it
            }
        }
    }
}