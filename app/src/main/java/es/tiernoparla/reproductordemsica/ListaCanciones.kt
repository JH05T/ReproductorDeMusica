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
    private lateinit var seekBar: SeekBar
    private lateinit var imageButtonCancionAnterior: AppCompatImageButton
    private lateinit var imageButtonPausaReproducir: AppCompatImageButton
    private lateinit var imageButtonCancionSiguiente: AppCompatImageButton

    private lateinit var reproductorDeCanciones: ReproductorDeCanciones
    var cancionActual = CancionActual(0)

    val canciones = arrayListOf(
        Cancion("Payphone", arrayListOf("Maroon 5", "Wiz Khalifa"), "@drawable/album_overexposed", R.raw.payphone___maroon5__wiz_khalifa, false),
        Cancion("Without You", arrayListOf("Ashes Remain"), "@drawable/album_what_i_ve_become", R.raw.without_you___ashes_remain, false),
        Cancion("Como un burro amarrado en la puerta del baile", arrayListOf("El Último De La Fila"), "@drawable/album_astronomia_razonable", R.raw.como_un_burro_amarrado_en_la_puerta_del_baile___el_ultimo_de_la_fila, false)
    )

    @SuppressLint("ClickableViewAccessibility", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_canciones)

        inicializarComponentes()

        imageButtonAbrir.setOnTouchListener { _, event ->

            when (event.action) {

                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {

                    abrirVideo()

                }

            }

            true

        }

        imageButtonCerrar.setOnTouchListener { _, event ->

            when (event.action) {

                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {

                    cerrarVideo()

                }

            }

            true

        }

        imageButtonCancionAnterior.setOnClickListener {

            reproductorDeCanciones.detenerCancion(canciones)

            if (cancionActual.valor > 0) {

                cancionActual.valor--

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual.valor], canciones,this)

            } else {

                cancionActual.valor = canciones.size - 1

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual.valor], canciones, this)

            }

            adaptadorRecyclerViewCanciones.notifyDataSetChanged()

        }

        imageButtonPausaReproducir.setOnClickListener {

            if (canciones[cancionActual.valor].reproduciendose){

                reproductorDeCanciones.pausarCancion(canciones[cancionActual.valor])

                imageButtonPausaReproducir.setImageResource(R.drawable.icono_reproducir)

            } else {

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual.valor], canciones, this)

                imageButtonPausaReproducir.setImageResource(R.drawable.icono_pausa)

            }

            adaptadorRecyclerViewCanciones.notifyDataSetChanged()

        }

        imageButtonCancionSiguiente.setOnClickListener {

            reproductorDeCanciones.detenerCancion(canciones)

            if (cancionActual.valor < canciones.size - 1) {

                cancionActual.valor++

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual.valor], canciones, this)

            } else {

                cancionActual.valor = 0

                reproductorDeCanciones.reproducirCancion(canciones[cancionActual.valor], canciones, this)
            }

            adaptadorRecyclerViewCanciones.notifyDataSetChanged()

        }


    }

    private fun inicializarComponentes() {

        imageButtonAbrir = findViewById(R.id.imageButtonAbrir)

        container = findViewById(R.id.container)
        container.visibility = View.GONE

        seekBar = findViewById(R.id.seekBar)

        videoView = findViewById(R.id.videoView)
        reproductorDeCanciones = ReproductorDeCanciones(videoView, seekBar)

        imageButtonCerrar = findViewById(R.id.imageButtonCerrar)
        imageButtonCerrar.visibility = View.GONE

        imageButtonCancionAnterior = findViewById(R.id.imageButtonCancionAnterior)
        imageButtonPausaReproducir= findViewById(R.id.imageButtonPausaReproducir)
        imageButtonCancionSiguiente = findViewById(R.id.imageButtonCancionSiguiente)

        recyclerViewCanciones = findViewById(R.id.recyclerViewCanciones)
        recyclerViewCanciones.layoutManager = LinearLayoutManager(this)
        adaptadorRecyclerViewCanciones = AdaptadorRecyclerViewCanciones(canciones, this, reproductorDeCanciones, cancionActual)
        recyclerViewCanciones.adapter = adaptadorRecyclerViewCanciones

    }

    fun abrirVideo(){

        container.visibility = View.VISIBLE
        imageButtonCerrar.visibility = View.VISIBLE
        imageButtonAbrir.visibility = View.GONE

        if (canciones[cancionActual.valor].reproduciendose){

            imageButtonPausaReproducir.setImageResource(R.drawable.icono_pausa)

        } else {

            imageButtonPausaReproducir.setImageResource(R.drawable.icono_reproducir)

        }

    }

    fun cerrarVideo(){

        container.visibility = View.GONE
        imageButtonCerrar.visibility = View.GONE
        imageButtonAbrir.visibility = View.VISIBLE

    }

    override fun onBackPressed() {

        container.visibility = View.GONE
        imageButtonCerrar.visibility = View.GONE
        imageButtonAbrir.visibility = View.VISIBLE

    }

}