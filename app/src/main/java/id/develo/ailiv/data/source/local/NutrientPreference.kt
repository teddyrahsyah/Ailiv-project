package id.develo.ailiv.data.source.local

import android.content.Context
import android.content.SharedPreferences
import id.develo.ailiv.data.source.local.entity.NutrientThreshold
import id.develo.ailiv.data.source.local.entity.UserModelEntity
import id.develo.ailiv.utils.Constant

internal class NutrientPreference(context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE)
    private var editor = preferences.edit()

    fun setUserDailyThreshold(value: UserModelEntity, threshold: NutrientThreshold) {
        // User Data
        editor.putString(Constant.USERNAME, value.username)
        editor.putString(Constant.PASSWORD, value.password)
        editor.putInt(Constant.AGE, value.age)
        editor.putString(Constant.GENDER, value.gender)
        editor.putBoolean(Constant.IS_LOGIN, value.isLogin)

        // User Threshold
        editor.putFloat(Constant.THRESHOLD_LEMAK, threshold.lemak)
        editor.putFloat(Constant.THRESHOLD_KALORI, threshold.kalori)
        editor.putFloat(Constant.THRESHOLD_KOLESTROL, threshold.kolestrol)
        editor.putFloat(Constant.THRESHOLD_PROTEIN, threshold.protein)
        editor.putFloat(Constant.THRESHOLD_KARBOHIDRAT, threshold.karbohidrat)
        editor.putFloat(Constant.THRESHOLD_SODIUM, threshold.sodium)
        editor.apply()
    }

    fun setAccumulateNutrients(value: UserModelEntity) {
        // User Daily Nutrients
        editor.putFloat(Constant.LEMAK, value.lemak)
        editor.putFloat(Constant.KALORI, value.kalori)
        editor.putFloat(Constant.KOLESTROL, value.kolestrol)
        editor.putFloat(Constant.PROTEIN, value.protein)
        editor.putFloat(Constant.KARBOHIDRAT, value.karbohidrat)
        editor.putFloat(Constant.SODIUM, value.sodium)
        editor.apply()
    }

    fun getUserData(): UserModelEntity {

        val model = UserModelEntity()
        model.username = preferences.getString(Constant.USERNAME, "")
        model.password = preferences.getString(Constant.PASSWORD, "")
        model.age = preferences.getInt(Constant.AGE, 0)
        model.gender = preferences.getString(Constant.GENDER, "")

        return model
    }

    // Login State
    fun getLoginState(): Boolean {
        return preferences.getBoolean(Constant.IS_LOGIN, false)
    }
    fun setLoginState(state: Boolean) {
        editor.putBoolean(Constant.IS_LOGIN, state)
        editor.apply()
    }

    fun getAccumulateNutrient(): UserModelEntity {

        val model = UserModelEntity()
        model.lemak = preferences.getFloat(Constant.LEMAK, 0F)
        model.kalori = preferences.getFloat(Constant.KALORI, 0F)
        model.kolestrol = preferences.getFloat(Constant.KOLESTROL, 0F)
        model.protein = preferences.getFloat(Constant.PROTEIN, 0F)
        model.karbohidrat = preferences.getFloat(Constant.KARBOHIDRAT, 0F)
        model.sodium = preferences.getFloat(Constant.SODIUM, 0F)

        return model
    }

    fun getUserThreshold(): NutrientThreshold {
        val threshold = NutrientThreshold()
        threshold.lemak = preferences.getFloat(Constant.THRESHOLD_LEMAK, 0F)
        threshold.kalori = preferences.getFloat(Constant.THRESHOLD_KALORI, 0F)
        threshold.kolestrol = preferences.getFloat(Constant.THRESHOLD_KOLESTROL, 0F)
        threshold.protein = preferences.getFloat(Constant.THRESHOLD_PROTEIN, 0F)
        threshold.karbohidrat = preferences.getFloat(Constant.THRESHOLD_KARBOHIDRAT, 0F)
        threshold.sodium = preferences.getFloat(Constant.THRESHOLD_SODIUM, 0F)

        return threshold
    }

    fun clearPreference() {
        editor.clear()
        editor.apply()
    }
}