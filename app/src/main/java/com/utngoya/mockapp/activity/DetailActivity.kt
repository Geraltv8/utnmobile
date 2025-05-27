package com.utngoya.mockapp.activity

import android.media.Image
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.utngoya.mockapp.R
import com.utngoya.mockapp.databinding.ActivityDetailBinding
import com.utngoya.mockapp.databinding.ActivityMainBinding
import com.utngoya.mockapp.model.ImagesModel

class DetailActivity : AppCompatActivity() {

    //Siempre instanciamos el binding
    private lateinit var b: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //primero inflamos el layout para poder usar sus elemenos con binding
        b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b.root)
        //------------------------------------------------------------
        //Aca recupero mi objeto que le pase desde el adapter
        val image: ImagesModel? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            //como veras aca es como si hiciera dos veces lo mismo
            //la diferencia es que en android 33 en adelante se hace asi:
            intent.getParcelableExtra("imageDetail", ImagesModel::class.java)
        } else {
            //y en android 33 para atras asi:
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("imageDetail")
            //asi que para mantener compatibilidad con cualquier versiontengo que hacer este if
            //para saber en que version estta el dispositivo (aca estoy usando la 31)
        }

        //ahora cargo la imagen si es que tengo una
        Picasso.get()
            .load(image?.url)
            .error(R.drawable.ic_launcher_foreground)
            .into(b.imgDetail)
        //y ahora el resto de los elementos
        b.txtDescription.text = image?.description ?: "description not found"
        b.txtOrigin.text = image?.origin ?: "origin not found"
        b.txtAuthor.text = image?.author ?: "author not found"
        b.txtYear.text = image?.year?.toString() ?: "year not found"
    }
}