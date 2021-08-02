package id.develo.ailiv.data.source.remote

import id.develo.ailiv.data.source.local.entity.NutritionEntity
import id.develo.ailiv.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getNutrition(filename: String): List<NutritionEntity> = jsonHelper.loadNutrition(filename)

}