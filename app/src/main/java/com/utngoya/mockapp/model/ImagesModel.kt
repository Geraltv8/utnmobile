package com.utngoya.mockapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*----------------------------------------------------------------------
Aca un paso importante que hice, agregué la posibilidad de hacer "parceable"
este modelo de datos, para ello, modifique el build.gradle.kts del modulo
en la tercera linea podran ver "id("kotlin-parcelize")", con esto podemos
pasar tooodo el objeto completo en el intent para ir a otra activity y
no tendriamos la necesidad de pasar elemento por elemento
---------------------------------------------------------------------- */
@Parcelize
data class ImagesModel(
    val url: String,
    val description: String,
    val origin: String,
    val author: String?,
    val year: Int
) : Parcelable
