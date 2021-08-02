package id.develo.ailiv.di

import android.content.Context
import id.develo.ailiv.data.source.NutritionRepository
import id.develo.ailiv.data.source.remote.RemoteDataSource
import id.develo.ailiv.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): NutritionRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return NutritionRepository.getInstance(remoteDataSource)
    }
}