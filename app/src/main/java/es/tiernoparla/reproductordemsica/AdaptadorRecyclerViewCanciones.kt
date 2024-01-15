package es.tiernoparla.reproductordemsica


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView

class AdaptadorRecyclerViewCanciones(private val canciones: List<Cancion>, private val context: Context) : RecyclerView.Adapter<AdaptadorRecyclerViewCanciones.ViewHolderCanciones>() {

    override fun getItemCount(): Int = canciones.size

    class ViewHolderCanciones(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewAlbum: ImageView = itemView.findViewById(R.id.imageViewAlbum)
        val textViewInfoCancion: TextView = itemView.findViewById(R.id.textViewInfoCancion)
        val buttonReproducirCancion: AppCompatImageButton = itemView.findViewById(R.id.imageButtonReproducirCancion)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCanciones {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cancion, parent, false)

        return ViewHolderCanciones(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolderCanciones, position: Int) {

        cargarImagen(canciones[position].imagenAlbum, holder.imageViewAlbum)
        holder.textViewInfoCancion.text = obtenerInformacionCancion(canciones[position])

        holder.buttonReproducirCancion.setOnClickListener {

            //canciones[position].reproducir();

        }

    }

    private fun obtenerInformacionCancion(cancion: Cancion): String {

        var msg: String

        msg = cancion.nombreCancion + "\n\n"

        for (artista in cancion.artistas){

            if (artista.equals(cancion.artistas.get(0))){

                msg += artista

            } else {

                msg += ", $artista"

            }

        }

        return msg

    }

    private fun cargarImagen(imagen: String, imageViewAlbum: ImageView) {

        val resId = imageViewAlbum.context.resources.getIdentifier(imagen, "drawable", imageViewAlbum.context.packageName)

        imageViewAlbum.setImageResource(resId)

    }

}