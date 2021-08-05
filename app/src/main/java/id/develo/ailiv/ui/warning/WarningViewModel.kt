package id.develo.ailiv.ui.warning

import androidx.lifecycle.ViewModel
import id.develo.ailiv.data.source.NutritionRepository
import id.develo.ailiv.data.source.local.entity.WarningEntity

class WarningViewModel(private val nutritionRepository: NutritionRepository): ViewModel() {

    fun getWarning(filename: String) : WarningEntity = nutritionRepository.getWarning(filename)
}