package id.develo.ailiv.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutritionEntity(
    var name: String,
    var value: Double
) : Parcelable
