package ph.edu.dlsu.mobdeve.mojicajera.ex3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val videoList:ArrayList<VideoModel>)
    :RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    var onButtonClick : ((VideoModel) -> Unit?)? = null
    var onItemClick : ((VideoModel) -> Unit)? = null

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val thumbnail : ImageView = itemView.findViewById(R.id.thumbnail)
        val videoTitle : TextView = itemView.findViewById(R.id.videoTitle)
        val videoCreator : TextView = itemView.findViewById(R.id.videoCreator)
        val videoDuration : TextView = itemView.findViewById(R.id.videoDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.thumbnail.setImageResource(video.thumbnail)
        holder.videoTitle.text = video.videoTitle
        holder.videoCreator.text = video.videoCreator
        holder.videoDuration.text = video.videoDuration

        var creatorButton = holder.itemView.findViewById<Button>(R.id.creatorButton)

        creatorButton.setOnClickListener{
            onButtonClick?.invoke(video)
        }

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(video)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}