package id.develo.ailiv.ui.dashboard

import androidx.lifecycle.ViewModel
import id.develo.ailiv.data.source.local.entity.NutritionEntity
import id.develo.ailiv.data.source.NutritionRepository

class DashboardViewModel(private val nutritionRepository: NutritionRepository): ViewModel() {

    fun getNutrition(filename: String): List<NutritionEntity> = nutritionRepository.getNutrition(filename)
}