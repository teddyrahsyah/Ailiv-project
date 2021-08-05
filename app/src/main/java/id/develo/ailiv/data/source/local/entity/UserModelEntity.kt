package id.develo.ailiv.data.source.local.entity

data class UserModelEntity(
    var username: String? = null,
    var password: String? = null,
    var age: Int = 0,
    var gender: String? = null,
    var isLogin: Boolean = false,

    var lemak: Float = 0F,
    var kalori: Float = 0F,
    var kolestrol: Float = 0F,
    var protein: Float = 0F,
    var karbohidrat: Float = 0F,
    var sodium: Float = 0F
)

data class NutrientThreshold(
    var lemak: Float = 0F,
    var kalori: Float = 0F,
    var kolestrol: Float = 0F,
    var protein: Float = 0F,
    var karbohidrat: Float = 0F,
    var sodium: Float = 0F,
)

data class AccumulatedNutrient(
    var name: String,
    var accumulatedValue: Double
)