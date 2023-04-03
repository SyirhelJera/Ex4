package ph.edu.dlsu.mobdeve.mojicajera.ex3

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

        videoList = ArrayList()

        videoList.add(VideoModel(R.drawable.a,"10 Minute Yoga Routine for Stress Relief", "PixelPenguin", "06:36"))
        videoList.add(VideoModel(R.drawable.b,"The Ultimate Guide to Making Perfect Homemade Pizza", "NinjaSlayer", "06:36"))
        videoList.add(VideoModel(R.drawable.c,"My Morning Routine for a Productive Day", "WildChildVibes", "06:36"))
        videoList.add(VideoModel(R.drawable.d,"How to Build a Successful Online Business from Scratch", "CosmicExplorer", "06:36"))
        videoList.add(VideoModel(R.drawable.e,"10 Minute Full-Body Workout for Busy People", "MysticMuse", "06:36"))
        videoList.add(VideoModel(R.drawable.a,"Trying Viral TikTok Recipes: Success or Fail?", "PixelPenguin", "06:36"))
        videoList.add(VideoModel(R.drawable.b,"What I Eat in a Day for a Healthy Lifestyle", "NinjaSlayer", "06:36"))
        videoList.add(VideoModel(R.drawable.c,"The Psychology Behind Procrastination and How to Overcome It", "WildChildVibes", "06:36"))
        videoList.add(VideoModel(R.drawable.d,"Unboxing and Reviewing the Latest Tech Gadgets", "CosmicExplorer", "06:36"))
        videoList.add(VideoModel(R.drawable.e,"5 Simple DIY Home Decor Ideas on a Budget", "MysticMuse", "06:36"))
        videoList.add(VideoModel(R.drawable.a,"The Science of Sleep: Tips for a Better Night's Rest", "PixelPenguin", "06:36"))
        videoList.add(VideoModel(R.drawable.b,"Reacting to My Old Embarrassing Photos and Videos", "NinjaSlayer", "06:36"))
        videoList.add(VideoModel(R.drawable.c,"Exploring Abandoned Places: Creepy or Cool?", "WildChildVibes", "06:36"))
        videoList.add(VideoModel(R.drawable.d,"Learning a New Language in 30 Days: My Journey and Tips", "CosmicExplorer", "06:36"))
        videoList.add(VideoModel(R.drawable.e,"The Future of Technology: Predictions and Innovations", "MysticMuse", "06:36"))


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
