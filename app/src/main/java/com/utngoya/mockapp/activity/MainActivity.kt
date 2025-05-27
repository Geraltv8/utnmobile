package com.utngoya.mockapp.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.utngoya.mockapp.R
import com.utngoya.mockapp.adapters.RecyclerAdapter
import com.utngoya.mockapp.databinding.ActivityMainBinding
import com.utngoya.mockapp.model.ImagesModel

class MainActivity : AppCompatActivity() {

    //Siempre instanciamos el binding
    private lateinit var b: ActivityMainBinding
    //acá tamabien instancio mi adapter
    private lateinit var recyclerAdapter: RecyclerAdapter

    //toda esta parte del onCreate queda siempre como esta.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //primero inflamos el layout para poder usar sus elemenos con binding
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        //------------------------------------------------------------
        val imageList = listOf(
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/2/25/Queen_%E2%80%93_montagem_%E2%80%93_new.png",
                "Queen",
                "Legendaria banda británica liderada por Freddie Mercury.",
                "Formada en 1970",
                500
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/2/26/Linkin_Park_in_2014.jpg",
                "Linkin Park",
                "Banda estadounidense de rock alternativo y nu metal.",
                "Formada en 1996",
                450
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/f/f7/Nirvana_around_1992.jpg",
                "Nirvana",
                "Pioneros del grunge liderados por Kurt Cobain.",
                "Formada en 1987",
                470
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/d/d1/Metallica_at_The_O2_London_2008.jpg",
                "Metallica",
                "Banda icónica de heavy metal de EE.UU.",
                "Formada en 1981",
                520
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/8/83/Pink_Floyd_1971.jpg",
                "Pink Floyd",
                "Banda de rock psicodélico y progresivo.",
                "Formada en 1965",
                510
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/6/60/The_Rolling_Stones_1972.jpg",
                "The Rolling Stones",
                "Una de las bandas más longevas del rock.",
                "Formada en 1962",
                530
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/4/4c/Green_Day_2010.jpg",
                "Green Day",
                "Banda de punk rock californiana.",
                "Formada en 1987",
                400
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/5/50/The_Beatles_in_America.JPG",
                "The Beatles",
                "Los cuatro grandes de Liverpool.",
                "Formada en 1960",
                600
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/a/a0/RHCP_Lollapalooza_2016.jpg",
                "Red Hot Chili Peppers",
                "Fusión de rock, funk y alternativo.",
                "Formada en 1983",
                470
            ),
            ImagesModel(
                "https://upload.wikimedia.org/wikipedia/commons/4/4f/ACDC_in_Manchester.jpg",
                "AC/DC",
                "Banda australiana de hard rock.",
                "Formada en 1973",
                550
            )
        )

        recyclerAdapter = RecyclerAdapter(imageList, this)
        b.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            this.adapter = this@MainActivity.recyclerAdapter
        }

        //Aqui obtengo la url de la ultima imagen clickeada
        b.btnLast.setOnClickListener {
            val sharedPreferences = getSharedPreferences("my_images_data", MODE_PRIVATE)
            val lastClickedItem = sharedPreferences.getString(
                "last_clicked_item", //aca va la key con la que guardamos nuestra url en el adapter
                "https://imagen-trucha.com" //aca va una imagen por defecto
            )

            Picasso.get()
                .load(lastClickedItem)
                .error(R.drawable.ic_launcher_foreground)
                .into(b.imgLast)
        }

        b.btndetail.setOnClickListener{
            //TODO: Aca deberiamos implementar que me muestre el detalle de la ultima imagen clickeada
        }
    }
}