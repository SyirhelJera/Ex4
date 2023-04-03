package ph.edu.dlsu.mobdeve.mojicajera.ex3

import android.os.Parcel
import android.os.Parcelable

data class VideoModel (
    val thumbnail: Int,
    val videoTitle :String,
    val videoCreator: String,
    val videoDuration: String) : Parcelable{
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

    companion object CREATOR : Parcelable.Creator<VideoModel> {
        override fun createFromParcel(parcel: Parcel): VideoModel {
            return VideoModel(parcel)
        }

        override fun newArray(size: Int): Array<VideoModel?> {
            return arrayOfNulls(size)
        }
    }
}