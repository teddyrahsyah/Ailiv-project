package id.develo.ailiv.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WarningEntity(
    var name: String? = null,
    var warningMessage :String? = null
) : Parcelable
