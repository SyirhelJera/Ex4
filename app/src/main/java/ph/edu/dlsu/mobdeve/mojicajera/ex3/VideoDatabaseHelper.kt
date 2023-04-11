import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ph.edu.dlsu.mobdeve.mojicajera.ex3.VideoModel

class VideoDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "video_database"
        private const val TABLE_NAME = "videos"
        private const val COL_THUMBNAIL = "thumbnail"
        private const val COL_VIDEO_TITLE = "video_title"
        private const val COL_VIDEO_CREATOR = "video_creator"
        private const val COL_VIDEO_DURATION = "video_duration"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = (
                "CREATE TABLE " + TABLE_NAME + "("
                + COL_THUMBNAIL + " INTEGER,"
                + COL_VIDEO_TITLE + " TEXT,"
                + COL_VIDEO_CREATOR + " TEXT,"
                + COL_VIDEO_DURATION + " TEXT"
                + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addVideo(videoModel: VideoModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_THUMBNAIL, videoModel.thumbnail)
        contentValues.put(COL_VIDEO_TITLE, videoModel.videoTitle)
        contentValues.put(COL_VIDEO_CREATOR, videoModel.videoCreator)
        contentValues.put(COL_VIDEO_DURATION, videoModel.videoDuration)
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result
    }

    fun getVideo(id: Int): VideoModel? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME, arrayOf(
                COL_THUMBNAIL,
                COL_VIDEO_TITLE,
                COL_VIDEO_CREATOR,
                COL_VIDEO_DURATION
            ), "$COL_THUMBNAIL=?", arrayOf(id.toString()), null, null, null, null
        )
        var videoModel: VideoModel? = null
        if (cursor.moveToFirst()) {
            val thumbnail = cursor.getInt(cursor.getColumnIndex(COL_THUMBNAIL))
            val videoTitle = cursor.getString(cursor.getColumnIndex(COL_VIDEO_TITLE))
            val videoCreator = cursor.getString(cursor.getColumnIndex(COL_VIDEO_CREATOR))
            val videoDuration = cursor.getString(cursor.getColumnIndex(COL_VIDEO_DURATION))
            videoModel = VideoModel(thumbnail, videoTitle, videoCreator, videoDuration)
        }
        cursor.close()
        db.close()
        return videoModel
    }

    fun getAllVideos(): List<VideoModel> {
        val videoList = ArrayList<VideoModel>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val thumbnail = cursor.getInt(cursor.getColumnIndex(COL_THUMBNAIL))
                val videoTitle = cursor.getString(cursor.getColumnIndex(COL_VIDEO_TITLE))
                val videoCreator = cursor.getString(cursor.getColumnIndex(COL_VIDEO_CREATOR))
                val videoDuration = cursor.getString(cursor.getColumnIndex(COL_VIDEO_DURATION))
                val videoModel = VideoModel(thumbnail, videoTitle, videoCreator, videoDuration)
                videoList.add(videoModel)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return videoList
    }
    fun updateVideo(videoModel: VideoModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_THUMBNAIL, videoModel.thumbnail)
        contentValues.put(COL_VIDEO_TITLE, videoModel.videoTitle)
        contentValues.put(COL_VIDEO_CREATOR, videoModel.videoCreator)
        contentValues.put(COL_VIDEO_DURATION, videoModel.videoDuration)
        val result = db.update(
            TABLE_NAME,
            contentValues,
            "$COL_THUMBNAIL = ?",
            arrayOf(videoModel.thumbnail.toString())
        )
        db.close()
        return result
    }

    fun deleteVideo(videoModel: VideoModel): Int {
        val db = this.writableDatabase
        val result = db.delete(
            TABLE_NAME,
            "$COL_THUMBNAIL = ?",
            arrayOf(videoModel.thumbnail.toString())
        )
        db.close()
        return result
    }

    fun deleteAllVideos(): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, null, null)
        db.close()
        return result
    }

//    fun addSubscription(userId: Int, creatorId: Int) {
//        val db = writableDatabase
//        val values = ContentValues().apply {
//            put(SubscriptionEntry.COLUMN_USER_ID, userId)
//            put(SubscriptionEntry.COLUMN_CREATOR_ID, creatorId)
//        }
//        db.insert(SubscriptionEntry.TABLE_NAME, null, values)
//        db.close()
//    }
//
//    fun addLike(userId: Int, videoId: Int, isLiked: Boolean) {
//        val db = writableDatabase
//        val values = ContentValues().apply {
//            put(LikeEntry.COLUMN_USER_ID, userId)
//            put(LikeEntry.COLUMN_VIDEO_ID, videoId)
//            put(LikeEntry.COLUMN_IS_LIKED, isLiked)
//        }
//        db.insert(LikeEntry.TABLE_NAME, null, values)
//        db.close()
//    }
//
//
//    fun addComment(userId: Int, videoId: Int, commentText: String, dateTime: String) {
//        val db = writableDatabase
//        val values = ContentValues().apply {
//            put(CommentEntry.COLUMN_USER_ID, userId)
//            put(CommentEntry.COLUMN_VIDEO_ID, videoId)
//            put(CommentEntry.COLUMN_COMMENT_TEXT, commentText)
//            put(CommentEntry.COLUMN_DATE_TIME, dateTime)
//        }
//        db.insert(CommentEntry.TABLE_NAME, null, values)
//        db.close()
//    }


}