package co.droidchef.tiphy.feature.trending.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.droidchef.tiphy.R
import co.droidchef.tiphy.network.model.response.Giphy
import com.bumptech.glide.Glide

class GifPreviewAdapter(
    private val context: Context,
    private val gifPreviews: ArrayList<Giphy>,
    private val onGiphyClickListener: OnGiphyClickListener
) :

    RecyclerView.Adapter<GifPreviewAdapter.GifPreviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifPreviewViewHolder {
        return GifPreviewViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grid_item_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return gifPreviews.size
    }

    override fun onBindViewHolder(holder: GifPreviewViewHolder, position: Int) {
        holder.bind(context, gifPreviews[position], onGiphyClickListener)
    }

    override fun getItemId(position: Int): Long {
        return gifPreviews[position].id.hashCode().toLong()
    }

    fun addMoreGiphies(listOfGiphy: ArrayList<Giphy>) {
        gifPreviews.addAll(listOfGiphy)
    }

    class GifPreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivGifPreview: ImageView = itemView.findViewById(R.id.iv_preview)

        fun bind(context: Context, giphy: Giphy, onGiphyClickListener: OnGiphyClickListener) {
            Glide.with(context).load(giphy.images.previewGif.url).into(ivGifPreview)
            ivGifPreview.setOnClickListener { onGiphyClickListener.onGiphyClick(giphy) }
        }
    }


}
