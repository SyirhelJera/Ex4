package ph.edu.dlsu.mobdeve.mojicajera.ex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Creator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator)

        val video = intent.getParcelableExtra<VideoContent>("Creator")
        if (video !=null){
            val thumbnail : ImageView = findViewById(R.id.thumbnail)
            val videoTitle : TextView = findViewById(R.id.videoTitle)
            val videoDuration : TextView = findViewById(R.id.videoDuration)
            val videoCreator : TextView = findViewById(R.id.videoCreator)

            videoTitle.text = video.videoTitle
            videoCreator.text = video.videoCreator
            videoDuration.text = video.videoDuration
            thumbnail.setImageResource(video.thumbnail)
    }
}
}