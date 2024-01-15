package es.tiernoparla.reproductordemsica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaCanciones : AppCompatActivity() {

    private lateinit var imageButton: AppCompatImageButton
    private lateinit var recyclerViewCanciones: RecyclerView
    private lateinit var adaptadorRecyclerViewCanciones: AdaptadorRecyclerViewCanciones

    public val canciones = arrayListOf(
        Cancion("Payphone", "Overexposed", arrayListOf("Maroon 5", "Wiz Khalifa"), "@drawable/album_overexposed", "cancion", "videoclip", false),
        Cancion("Without You", "What I've Become", arrayListOf("Ashes Remain"), "@drawable/album_what_i_ve_become", "cancion", "videoclip", false),
        Cancion("Como un burro amarrado en la puerta del baile", "Astronomía Razonable", arrayListOf("El Último De La Fila"), "@drawable/album_astronomia_razonable", "cancion", "videoclip", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_canciones)

        inicializarComponentes()


    }

    private fun inicializarComponentes() {

        imageButton = findViewById(R.id.imageButton)
        recyclerViewCanciones = findViewById(R.id.recyclerViewCanciones)

        recyclerViewCanciones.layoutManager = LinearLayoutManager(this)

        adaptadorRecyclerViewCanciones = AdaptadorRecyclerViewCanciones(canciones, this)

        recyclerViewCanciones.adapter = adaptadorRecyclerViewCanciones

    }

}