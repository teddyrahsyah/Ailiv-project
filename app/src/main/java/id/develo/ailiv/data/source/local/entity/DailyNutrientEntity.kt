package id.develo.ailiv.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyNutrientEntity (
    var username: String? = null,
    var password: String? = null,
    var age: Int = 0,
    var gender: String? = null,
    var lemak: Float = 0F,
    var kalori: Float = 0F,
    var kolestrol: Float = 0F,
    var protein: Float = 0F,
    var karbohidrat: Float = 0F,
    var sodium: Float = 0F
) : Parcelable