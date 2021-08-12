package id.develo.ailiv.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuggestionEntity(
    var name: String,
    var value: Float,
    var unit: String
) : Parcelable
