package ua.kpi.comsys.ip8418.images

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8418.R
import ua.kpi.comsys.ip8418.movies.Movie

class ImagesAdapter(private var images: MutableList<Uri>) :
        RecyclerView.Adapter<ImagesAdapter.ImageHolder>() {

    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView? = itemView.findViewById(R.id.image)
    }

    override fun getItemCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val itemView = LayoutInflater.from(parent.context)
                                     .inflate(R.layout.fragment_image, parent, false)
        return ImageHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.image?.setImageURI(images[position])
    }
}