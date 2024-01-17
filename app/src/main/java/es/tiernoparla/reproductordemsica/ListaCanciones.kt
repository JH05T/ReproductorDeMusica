package es.tiernoparla.reproductordemsica

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaCanciones : AppCompatActivity() {

    private lateinit var imageButtonAbrir: AppCompatImageButton
    private lateinit var recyclerViewCanciones: RecyclerView
    private lateinit var adaptadorRecyclerViewCanciones: AdaptadorRecyclerViewCanciones

    private lateinit var container: ConstraintLayout
    private lateinit var videoView: VideoView
    private lateinit var imageButtonCerrar: AppCompatImageButton
    private lateinit var SeekBar: SeekBar
    private lateinit var imageButtonCancionAnterior: AppCompatImageButton
    private lateinit var imageButtonPausaReproducir: AppCompatImageButton
    private lateinit var imageButtonCancionSiguiente: AppCompatImageButton

    private var reproductorDeCanciones: ReproductorDeCanciones = ReproductorDeCanciones()

    val canciones = arrayListOf(
        Cancion("Payphone", arrayListOf("Maroon 5", "Wiz Khalifa"), "@drawable/album_overexposed", R.raw.payphone___maroon5__wiz_khalifa, false),
        Cancion("Without You", arrayListOf("Ashes Remain"), "@drawable/album_what_i_ve_become", R.raw.without_you___ashes_remain, false),
        Cancion("Como un burro amarrado en la puerta del baile", arrayListOf("El Último De La Fila"), "@drawable/album_astronomia_razonable", R.raw.como_un_burro_amarrado_en_la_puerta_del_baile___el_ultimo_de_la_fila, false)
    )

    @SuppressLint("ClickableViewAccessibility", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_canciones)

        var cancionActual = 0

        inicializarComponentes()

        imageButtonAbrir.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    container.visibility = View.VISIBLE
                    imageButtonCerrar.visibility = View.VISIBLE
                    imageButtonAbrir.visibility = View.GONE
                }
                MotionEvent.ACTION_UP -> {
                    container.visibility = View.VISIBLE
                    imageButtonCerrar.visibility = View.VISIBLE
                    imageButtonAbrir.visibility = View.GONE
                }
            }
            true
        }

        imageButtonCerrar.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    container.visibility = View.GONE
                    imageButtonCerrar.visibility = View.GONE
                    imageButtonAbrir.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_UP -> {
                    container.visibility = View.GONE
                    imageButtonCerrar.visibility = View.GONE
                    imageButtonAbrir.visibility = View.VISIBLE
                }
            }
            true
        }

        imageButtonCancionAnterior.setOnClickListener {

            reproductorDeCanciones.detenerCancion(canciones)

            if (cancionActual > 0) {

                cancionActual--

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual], this)

            } else {

                cancionActual = canciones.size - 1

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual], this)

            }

            adaptadorRecyclerViewCanciones.notifyDataSetChanged()

        }

        imageButtonPausaReproducir.setOnClickListener {

            if (canciones[cancionActual].reproduciendose){

                reproductorDeCanciones.pausarCancion(canciones[cancionActual])

            } else {

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual], this)

            }

            adaptadorRecyclerViewCanciones.notifyDataSetChanged()

        }

        imageButtonCancionSiguiente.setOnClickListener {

            reproductorDeCanciones.detenerCancion(canciones)

            if (cancionActual < canciones.size - 1) {

                cancionActual++

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual], this)

            } else {

                cancionActual = 0

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual], this)
            }

            adaptadorRecyclerViewCanciones.notifyDataSetChanged()

        }


    }

    private fun inicializarComponentes() {

        imageButtonAbrir = findViewById(R.id.imageButtonAbrir)
        recyclerViewCanciones = findViewById(R.id.recyclerViewCanciones)

        recyclerViewCanciones.layoutManager = LinearLayoutManager(this)

        adaptadorRecyclerViewCanciones = AdaptadorRecyclerViewCanciones(canciones, this, reproductorDeCanciones)

        recyclerViewCanciones.adapter = adaptadorRecyclerViewCanciones

        container = findViewById(R.id.container)

        container.visibility = View.GONE

        videoView = findViewById(R.id.videoView)

        imageButtonCerrar = findViewById(R.id.imageButtonCerrar)

        imageButtonCerrar.visibility = View.GONE

        SeekBar = findViewById(R.id.seekBar)

        imageButtonCancionAnterior = findViewById(R.id.imageButtonCancionAnterior)

        imageButtonPausaReproducir= findViewById(R.id.imageButtonPausaReproducir)

        imageButtonCancionSiguiente = findViewById(R.id.imageButtonCancionSiguiente)

    }

}