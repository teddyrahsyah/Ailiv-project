package id.develo.ailiv.data.source.local

import android.content.Context
import id.develo.ailiv.data.source.local.entity.DailyNutrientEntity

internal class NutrientPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "nutrient_pref"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val AGE = "age"
        private const val GENDER = "gender"
        private const val LEMAK = "lemak"
        private const val KALORI = "kalori"
        private const val KOLESTROL = "kolestrol"
        private const val PROTEIN = "protein"
        private const val KARBOHIDRAT = "karbohidrat"
        private const val SODIUM = "sodium"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setDailyNutrient(value: DailyNutrientEntity) {
        val editor = preferences.edit()
        editor.putString(USERNAME, value.username)
        editor.putString(PASSWORD, value.password)
        editor.putInt(AGE, value.age)
        editor.putString(GENDER, value.gender)
        editor.putFloat(LEMAK, value.lemak)
        editor.putFloat(KALORI, value.kalori)
        editor.putFloat(KOLESTROL, value.kolestrol)
        editor.putFloat(PROTEIN, value.protein)
        editor.putFloat(KARBOHIDRAT, value.karbohidrat)
        editor.putFloat(SODIUM, value.sodium)
        editor.apply()
    }

    fun getDailyNutrient(): DailyNutrientEntity {
        val model = DailyNutrientEntity()
        model.username = preferences.getString(USERNAME, "")
        model.password = preferences.getString(PASSWORD, "")
        model.age = preferences.getInt(AGE, 0)
        model.gender = preferences.getString(GENDER, "")
        model.lemak = preferences.getFloat(LEMAK, 0F)
        model.kalori = preferences.getFloat(KALORI, 0F)
        model.kolestrol = preferences.getFloat(KOLESTROL, 0F)
        model.protein = preferences.getFloat(PROTEIN, 0F)
        model.karbohidrat = preferences.getFloat(KARBOHIDRAT, 0F)
        model.sodium = preferences.getFloat(SODIUM, 0F)

        return model
    }
}