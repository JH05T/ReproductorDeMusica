package es.tiernoparla.reproductordemsica

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class ReproductorDeCanciones {

    private var mediaPlayer: MediaPlayer? = null

    fun reproducirCancion(cancion: Cancion, context: Context) {

        if (mediaPlayer == null) {

            val uri = Uri.parse("android.resource://${context.packageName}/${cancion.cancion}")

            mediaPlayer = MediaPlayer.create(context, uri)

            mediaPlayer?.setOnCompletionListener {

                cancion.reproduciendose = false

            }


        }

        mediaPlayer?.start()

        cancion.reproduciendose = true

    }

    fun pausarCancion() {

        if (mediaPlayer?.isPlaying == true) {

            mediaPlayer?.pause()

        }

    }

    fun detenerCancion(canciones: List<Cancion>){

        for (cancion in canciones){

            cancion.reproduciendose = false

        }

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

    }

}