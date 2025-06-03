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
        Picasso.get()
            .load("https://dynamic.brandcrowd.com/template/preview/design/2ed9e22f-e7d7-4997-b9f0-603dff68d3d1?v=4&designTemplateVersion=2&size=design-preview-standalone-1x")
            .resize(600, 200)
            .into(b.imgBanner)

        val imageList = listOf(
            ImagesModel(
                "https://media.gettyimages.com/id/109767921/es/foto/drummer-roger-taylor-singer-freddie-mercury-guitarist-brian-may-and-bassist-john-deacon-of.jpg?s=612x612&w=gi&k=20&c=uGCnpa77kIkTc5HcITUCfe7Mh0pWk12h4kXsnvehWvs=",
                "Queen - Legendaria banda británica liderada por Freddie Mercury.",
                "www.gettyimages.es",
                "Hulton Archive",
                1973
            ),
            ImagesModel(
                "https://media.gettyimages.com/id/2205606892/es/foto/los-angeles-california-emily-armstrong-joe-hahn-mike-shinoda-and-colin-brittain-of-linkin-park.jpg?s=1024x1024&w=gi&k=20&c=Ypk8Njss44ZKYpz7UUbnCM6qQ4f5uJKto0YBBRlc90U=",
                "Linkin Park - Banda estadounidense de rock alternativo y nu metal.",
                "www.gettyimages.es",
                "Getty Images North America",
                2025
            ),
            ImagesModel(
                "https://media.gettyimages.com/id/181809003/es/foto/american-rock-group-nirvana-backstage-in-frankfurt-germany-12th-november-1991-left-to-right.jpg?s=612x612&w=gi&k=20&c=nq_tqdwaPMI7PVKHB55_vyu8cadZEMP2PdHbdoB1uRw=",
                "Nirvana - Pioneros del grunge liderados por Kurt Cobain.",
                "www.gettyimages.es",
                "Redferns",
                1991            ),
            ImagesModel(
                "https://media.gettyimages.com/id/1481711679/es/foto/los-angeles-california-robert-trujillo-james-hetfield-lars-ulrich-and-kirk-hammett-of.jpg?s=1024x1024&w=gi&k=20&c=UqNR9PKDzV004oh-k41gYuMOonnYC6j4D7PyxeB705k=",
                "Metallica - Banda icónica de heavy metal de EE.UU.",
                "www.gettyimages.es",
                "Getty Images North America",
                2023
            ),
            ImagesModel(
                "https://media.gettyimages.com/id/73909162/es/foto/united-kingdom-pink-floyd-pose-for-a-publicity-shot-circa-1973.jpg?s=1024x1024&w=gi&k=20&c=m0lGjBaMg5wiTRLaZ_cmmbWexTSvTx0vEqT6Zhrqw-w=",
                "Pink Floyd - Banda de rock psicodélico y progresivo.",
                "www.gettyimages.es",
                "Michael Ochs Archives",
                1973
            ),
            ImagesModel(
                "https://media.gettyimages.com/id/1745676838/es/foto/new-york-new-york-ronnie-wood-steve-jordan-mick-jagger-and-keith-richards-perform-during-the.jpg?s=1024x1024&w=gi&k=20&c=AD05aDSnvBv8Z8VkH3F8zNxzJzhB24N7CUlq9A3Qbs0=",
                "The Rolling Stones - Una de las bandas más longevas del rock." ,
                "www.gettyimages.es",
                "Getty Images North America",
                2023
            ),
        )
        //------------------------------------------------------------------------------------------
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