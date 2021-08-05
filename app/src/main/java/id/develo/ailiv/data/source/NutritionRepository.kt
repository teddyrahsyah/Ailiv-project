package id.develo.ailiv.data.source

import id.develo.ailiv.data.source.local.entity.NutritionEntity
import id.develo.ailiv.data.source.local.entity.WarningEntity
import id.develo.ailiv.data.source.remote.RemoteDataSource

class NutritionRepository private constructor(private val remoteDataSource: RemoteDataSource): DataSource {

    companion object {
        @Volatile
        private var instance: NutritionRepository? = null
        fun getInstance(remoteData: RemoteDataSource): NutritionRepository =
            instance ?: synchronized(this) {
                instance ?: NutritionRepository(remoteData).apply { instance = this }
            }
    }

    override fun getNutrition(filename: String): List<NutritionEntity> {
        val nutritionResponse = remoteDataSource.getNutrition(filename)
        val nutritionList = ArrayList<NutritionEntity>()
        for (item in nutritionResponse) {
            val nutrition = NutritionEntity(
                item.name,
                item.value
            )

            nutritionList.add(nutrition)
        }
        return nutritionList
    }

    override fun getWarning(filename: String): WarningEntity {
        val warningResponse = remoteDataSource.getWarning(filename)

        return WarningEntity(warningResponse.name, warningResponse.warningMessage)
    }
}