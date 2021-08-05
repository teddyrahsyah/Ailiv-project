package id.develo.ailiv.utils

import android.content.Context
import id.develo.ailiv.data.source.local.entity.NutritionEntity
import id.develo.ailiv.data.source.local.entity.WarningEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(filename: String): String? {
        return try {
            val `is` = context.assets.open(filename)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadNutrition(filename: String): List<NutritionEntity> {
        val listNutrition = ArrayList<NutritionEntity>()

        try {
            val result = parsingFileToString(filename)
            if (result != null) {
                val nutritionObject = JSONObject(result)
                val dataArray = nutritionObject.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val nutrition = dataArray.getJSONObject(i)
                    val name = nutrition.getString("name")
                    val value = nutrition.getDouble("value")

                    val nutritionValue = NutritionEntity(name, value)
                    listNutrition.add(nutritionValue)
                }

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listNutrition
    }

    fun loadWarning(filename: String): WarningEntity {
        var warningValue = WarningEntity()

        try {
            val result = parsingFileToString(filename)
            if (result != null) {
                val warningObject = JSONObject(result)
                val name = warningObject.getString("name")
                val warningMessage = warningObject.getString("warning")

                 warningValue = WarningEntity(name, warningMessage)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return warningValue
    }
}