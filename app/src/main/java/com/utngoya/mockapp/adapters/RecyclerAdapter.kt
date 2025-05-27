package com.utngoya.mockapp.adapters

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utngoya.mockapp.activity.DetailActivity
import com.utngoya.mockapp.databinding.ItemViewBinding
import com.utngoya.mockapp.model.ImagesModel

class RecyclerAdapter(
    private val imagesList: List<ImagesModel>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    //Aca conectamos nuestro adapter con la vista item_view.xml
    inner class RecyclerViewHolder(val b: ItemViewBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): RecyclerViewHolder {
        val b = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(b)
    }

    // Vincula los datos del ítem con las vistas
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = imagesList[position] // Obtiene el ítem en la posición actual

    // Librería Picasso para cargar la imagen y mostrarla
        Picasso.get().load(item.url).into(holder.b.imgItem)

    //Aqui hacemos click (o mejor dicho tap) sobre la imagen
        holder.b.imgItem.setOnClickListener {
            //Uso sharedPreferences para guardar la ultima imagen en la que hice click
            val sharedPreferences = context.getSharedPreferences("my_images_data", MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString("last_clicked_item", item.url)
                apply()
            }
            //A partir de acá vamos a movernos a otra pantalla cuando se hace click en la imagen
            // instanciamos e inicializamos la clase hacia donde vamos a ir mediante el intent
            val intent = Intent(context, DetailActivity::class.java)
            // Paso el objeto completo asi puedo acceder a todos sus datos
            intent.putExtra("imageDetail", item)
            context.startActivity(intent) // Se lanza la nueva actividad
        }
    }

    // Devuelve la cantidad de ítems que tiene la lista
    override fun getItemCount(): Int = imagesList.size
}