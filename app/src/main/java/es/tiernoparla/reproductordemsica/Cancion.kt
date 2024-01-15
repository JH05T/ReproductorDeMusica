package es.tiernoparla.reproductordemsica

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class Cancion(
    var nombreCancion: String,
    var nombreAlbum: String,
    var artistas: List<String>,
    var imagenAlbum: String,
    var cancion: String,
    var videoclip: String,
    var reproduciendose: Boolean
) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(

        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: mutableListOf(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readBoolean()

    )


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(nombreCancion)
        parcel.writeString(nombreAlbum)
        parcel.writeStringList(artistas)
        parcel.writeString(imagenAlbum)
        parcel.writeString(cancion)
        parcel.writeString(videoclip)
        parcel.writeBoolean(reproduciendose)

    }

    override fun describeContents(): Int {

        return 0

    }

    companion object CREATOR : Parcelable.Creator<Cancion> {

        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Cancion {

            return Cancion(parcel)

        }

        override fun newArray(size: Int): Array<Cancion?> {

            return arrayOfNulls(size)

        }

    }

}
