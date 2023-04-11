package ph.edu.dlsu.mobdeve.mojicajera.ex3

import VideoDatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoList: ArrayList<VideoModel>
    private lateinit var videoAdapter: VideoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Create a new instance of the VideoDatabaseHelper class
        val dbHelper = VideoDatabaseHelper(this)

        dbHelper.deleteAllVideos()

        val newVideo1 = VideoModel(thumbnail = R.drawable.a, videoTitle = "Funny Cat Videos", videoCreator = "CatLover123", videoDuration = "0:45")
        val newVideo2 = VideoModel(thumbnail = R.drawable.b, videoTitle = "Cooking Tutorial", videoCreator = "ChefGordon", videoDuration = "5:15")
        val newVideo3 = VideoModel(thumbnail = R.drawable.c, videoTitle = "Gardening Tips", videoCreator = "GreenThumb101", videoDuration = "2:30")
        val newVideo4 = VideoModel(thumbnail = R.drawable.d, videoTitle = "DIY Home Decor", videoCreator = "HomeDesigns", videoDuration = "3:20")
        val newVideo5 = VideoModel(thumbnail = R.drawable.e, videoTitle = "Fitness Workout", videoCreator = "FitnessFanatic", videoDuration = "10:00")
        val newVideo6 = VideoModel(thumbnail = R.drawable.a, videoTitle = "Nature Documentary", videoCreator = "CatLover123", videoDuration = "45:00")
        val newVideo7 = VideoModel(thumbnail = R.drawable.b, videoTitle = "Travel Vlog", videoCreator = "ChefGordon", videoDuration = "8:30")
        val newVideo8 = VideoModel(thumbnail = R.drawable.c, videoTitle = "Music Performance", videoCreator = "GreenThumb101", videoDuration = "4:45")
        val newVideo9 = VideoModel(thumbnail = R.drawable.d, videoTitle = "Comedy Skit", videoCreator = "HomeDesigns", videoDuration = "2:00")
        val newVideo10 = VideoModel(thumbnail = R.drawable.e, videoTitle = "Art Tutorial", videoCreator = "FitnessFanatic", videoDuration = "6:15")
        val newVideo11 = VideoModel(thumbnail = R.drawable.a, videoTitle = "Interview with a CEO", videoCreator = "CatLover123", videoDuration = "7:50")
        val newVideo12 = VideoModel(thumbnail = R.drawable.b, videoTitle = "Gameplay Livestream", videoCreator = "ChefGordon", videoDuration = "2:00:00")
        val newVideo13 = VideoModel(thumbnail = R.drawable.c, videoTitle = "Fashion Tips", videoCreator = "GreenThumb101", videoDuration = "3:15")
        val newVideo14 = VideoModel(thumbnail = R.drawable.d, videoTitle = "Movie Review", videoCreator = "CinemaCritics", videoDuration = "10:30")
        val newVideo15 = VideoModel(thumbnail = R.drawable.e, videoTitle = "Artistic Painting", videoCreator = "HomeDesigns", videoDuration = "1:45:00")


        // Insert the new video into the database
        dbHelper.addVideo(newVideo1)
        dbHelper.addVideo(newVideo2)
        dbHelper.addVideo(newVideo3)
        dbHelper.addVideo(newVideo4)
        dbHelper.addVideo(newVideo5)
        dbHelper.addVideo(newVideo6)
        dbHelper.addVideo(newVideo7)
        dbHelper.addVideo(newVideo8)
        dbHelper.addVideo(newVideo9)
        dbHelper.addVideo(newVideo10)
        dbHelper.addVideo(newVideo11)
        dbHelper.addVideo(newVideo12)
        dbHelper.addVideo(newVideo13)
        dbHelper.addVideo(newVideo14)
        dbHelper.addVideo(newVideo15)

        // Close the database
        dbHelper.close()

        videoList = dbHelper.getAllVideos() as ArrayList<VideoModel>

        //Sort Button
        val myButton = findViewById<Button>(R.id.filterButton)
        myButton.setOnClickListener {
            videoList.sortBy { it.videoCreator }
            videoAdapter.notifyDataSetChanged()
        }

        videoAdapter = VideoAdapter(videoList)
        recyclerView.adapter = videoAdapter

        videoAdapter.onItemClick = {
            val intent = Intent(this, Detailed::class.java)
            intent.putExtra("Video", it)
            startActivity(intent)
        }

        videoAdapter.onButtonClick = {
            val intent = Intent (this, Creator::class.java)
            intent.putExtra("Creator", it)
            startActivity(intent)
        }

    }
}
