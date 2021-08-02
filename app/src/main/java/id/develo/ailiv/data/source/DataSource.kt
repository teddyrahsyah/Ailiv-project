package id.develo.ailiv.data.source

import id.develo.ailiv.data.source.local.entity.NutritionEntity

interface DataSource {

    fun getNutrition(filename: String): List<NutritionEntity>
}