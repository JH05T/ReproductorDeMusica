package es.tiernoparla.reproductordemsica

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.widget.SeekBar
import android.widget.VideoView

class ReproductorDeCanciones(private val videoView: VideoView, private val seekBar: SeekBar) {

    private var uriActual: Uri? = null
    private val handler = Handler()

    fun reproducirCancion(cancion: Cancion, canciones: List<Cancion>, context: Context) {

        val uri = Uri.parse("android.resource://${context.packageName}/${cancion.cancion}")

        if (uriActual != uri) {

            detenerCanciones(canciones)

            videoView.setVideoURI(uri)

            uriActual = uri

        }

        videoView.setOnPreparedListener {

            configurarSeekBar(cancion)

        }

        videoView.setOnCompletionListener {

            cancion.reproduciendose = false

        }

        videoView.start()

        cancion.reproduciendose = true

    }

    fun pausarCancion(cancion: Cancion) {

        if (videoView.isPlaying) {

            videoView.pause()

            cancion.reproduciendose = false

        }

    }

    fun detenerCanciones(canciones: List<Cancion>) {

        for (cancion in canciones) {

            cancion.reproduciendose = false

        }

        videoView.pause()

        videoView.seekTo(0)

    }

    fun configurarSeekBar(cancion: Cancion) {

        seekBar.max = videoView.duration

        val runnable = object : Runnable {

            override fun run() {

                if (videoView != null) {

                    seekBar.progress = videoView.currentPosition

                    handler.postDelayed(this, 10)

                }

            }

        }

        handler.postDelayed(runnable, 10)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (fromUser) {

                    videoView.seekTo(progress)

                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

    }

}
