package id.develo.ailiv.ui.suggestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import id.develo.ailiv.R
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.data.source.local.entity.SuggestionEntity
import id.develo.ailiv.databinding.ActivitySuggestionBinding

class SuggestionActivity : AppCompatActivity() {

    companion object {
        const val EXTRA = "extra"
    }

    private lateinit var binding: ActivitySuggestionBinding

    private lateinit var mNutrientPreference: NutrientPreference
    private lateinit var listSuggestion: ArrayList<SuggestionEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mNutrientPreference = NutrientPreference(this)
        val thresholds = mNutrientPreference.getUserThreshold()

        val list = intent.getStringArrayListExtra(EXTRA)
        Log.d("CEK", list.toString())

        var name: String
        var value: Float
        var unit: String
        listSuggestion = ArrayList()
        for (i: String in list!!) {

            if (i == "Lemak") {
                name = i
                value = thresholds.lemak
                unit = "g"
                listSuggestion.add(SuggestionEntity(name, value, unit))
            }
            if (i == "Kalori") {
                name = i
                value = thresholds.kalori
                unit = "kcal"
                listSuggestion.add(SuggestionEntity(name, value, unit))
            }
            if (i == "Kolestrol") {
                name = i
                value = thresholds.kolestrol
                unit = "mg"
                listSuggestion.add(SuggestionEntity(name, value, unit))
            }
            if (i == "Protein") {
                name = i
                value = thresholds.protein
                unit = "g"
                listSuggestion.add(SuggestionEntity(name, value, unit))
            }
            if (i == "Karbohidrat") {
                name = i
                value = thresholds.karbohidrat
                unit = "g"
                listSuggestion.add(SuggestionEntity(name, value, unit))
            }
            if (i == "Sodium") {
                name = i
                value = thresholds.sodium
                unit = "mg"
                listSuggestion.add(SuggestionEntity(name, value, unit))
            }
        }

        val suggestionAdapter = SuggestionAdapter()
        suggestionAdapter.setSuggestion(listSuggestion)

        with(binding.rvSuggestion) {
            layoutManager = LinearLayoutManager(this@SuggestionActivity)
            setHasFixedSize(true)
            adapter = suggestionAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}