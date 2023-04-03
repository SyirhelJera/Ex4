package ph.edu.dlsu.mobdeve.mojicajera.ex3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "video.db"
        private const val TBL_VIDEO = "tbl_video"
        private const val ID = "id"
        private const val THUMBNAIL = "thumbnail"
        private const val TITLE = "title"
        private const val CREATOR = "creator"
        private const val DURATION = "duration"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblVideo = ("CREATE TABLE" + TBL_VIDEO + "("
                + ID + "INTEGER PRIMARY KEY," + THUMBNAIL + "INTEGER,"
                + TITLE + "TEXT,"
                + CREATOR + "TEXT,"
                + DURATION + "TEXT"
                + ")")

        db?.execSQL(createTblVideo)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_VIDEO")
        onCreate(db)
    }

    //fun insertComment

}