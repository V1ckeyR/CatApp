package ua.kpi.comsys.ip8418.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kpi.comsys.ip8418.R

class ImagesAdapter(private var images: List<Image>) :
        RecyclerView.Adapter<ImagesAdapter.ImageHolder>() {

    fun update(newImages: List<Image>) {
        images = newImages
        notifyDataSetChanged()
    }

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
        Picasso.get()
                .load(images[position].webformatURL)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_images)
                .into(holder.image)
    }
}