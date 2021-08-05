package id.develo.ailiv.data.source

import id.develo.ailiv.data.source.local.entity.NutritionEntity
import id.develo.ailiv.data.source.local.entity.WarningEntity

interface DataSource {

    fun getNutrition(filename: String): List<NutritionEntity>

    fun getWarning(filename: String): WarningEntity
}