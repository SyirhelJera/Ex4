package ph.edu.dlsu.mobdeve.mojicajera.ex3

import android.os.Parcel
import android.os.Parcelable

data class VideoContent (val thumbnail: Int, val videoTitle :String, val videoCreator: String, val videoDuration: String) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(thumbnail)
        parcel.writeString(videoTitle)
        parcel.writeString(videoCreator)
        parcel.writeString(videoDuration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoContent> {
        override fun createFromParcel(parcel: Parcel): VideoContent {
            return VideoContent(parcel)
        }

        override fun newArray(size: Int): Array<VideoContent?> {
            return arrayOfNulls(size)
        }
    }
}