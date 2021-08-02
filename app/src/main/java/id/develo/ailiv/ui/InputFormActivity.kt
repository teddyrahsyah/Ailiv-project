package id.develo.ailiv.ui

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.develo.ailiv.R
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.data.source.local.entity.DailyNutrientEntity
import id.develo.ailiv.databinding.ActivityInputFormBinding

class InputFormActivity : AppCompatActivity(){

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
    }

    private lateinit var binding: ActivityInputFormBinding

    private lateinit var dailyNutrient: DailyNutrientEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.brand_logo)
            .into(binding.ivBrandLogo)

        binding.btnContinue.setOnClickListener {
            // User Data
            val username = binding.edtUsername.editText?.text.toString().trim()
            val password = binding.edtPassword.editText?.text.toString().trim()
            val age = binding.edtAge.editText?.text.toString()

            val radioGroup = binding.radioGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(radioGroup)
            val gender = selectedGender.text.toString()

            // Form Validation
            if (username.isEmpty()) {
                binding.edtUsername.error = FIELD_REQUIRED
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtUsername.error = FIELD_REQUIRED
                return@setOnClickListener
            }

            if (age.isEmpty()) {
                binding.edtUsername.error = FIELD_REQUIRED
                return@setOnClickListener
            }

            // Nutrients Data
            var lemak = 0F
            var kalori = 0F
            var kolestrol = 0F
            var protein = 0F
            var karbohidrat = 0F
            var sodium = 0F

            val intAge = age.toInt()

            if (gender == "Male" && intAge >= 19 && intAge <= 20) {
                lemak = 75F
                kalori = 2650F
                kolestrol = 299F
                protein = 65F
                karbohidrat = 430F
                sodium = 1500F
            }

            saveDataToPref(
                username,
                password,
                intAge,
                gender,
                lemak,
                kalori,
                kolestrol,
                protein,
                karbohidrat,
                sodium
            )

            Intent(this@InputFormActivity, DashboardActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun saveDataToPref(
        username: String,
        password: String,
        age: Int,
        gender: String,
        lemak: Float,
        kalori: Float,
        kolestrol: Float,
        protein: Float,
        karbohidrat: Float,
        sodium: Float,
    ) {
        val nutrientPreference = NutrientPreference(this)
        dailyNutrient = nutrientPreference.getDailyNutrient()

        dailyNutrient.username = username
        dailyNutrient.password = password
        dailyNutrient.age = age
        dailyNutrient.gender = gender
        dailyNutrient.lemak = lemak
        dailyNutrient.kalori = kalori
        dailyNutrient.kolestrol = kolestrol
        dailyNutrient.protein = protein
        dailyNutrient.karbohidrat = karbohidrat
        dailyNutrient.sodium = sodium

        nutrientPreference.setDailyNutrient(dailyNutrient)
    }


}
//// User Data
//val username = binding.edtUsername.editText?.text.toString().trim()
//val password = binding.edtPassword.editText?.text.toString().trim()
//val age = binding.edtAge.editText?.text.toString()
//
//val radioGroup = binding.radioGroup.checkedRadioButtonId
//val selectedGender = findViewById<RadioButton>(radioGroup)
//val gender = selectedGender.text.toString()
//
//// Form Validation
//if (username.isEmpty()) {
//    binding.edtUsername.error = InputFormActivity.FIELD_REQUIRED
//    return
//}
//
//if (password.isEmpty()) {
//    binding.edtUsername.error = InputFormActivity.FIELD_REQUIRED
//    return
//}
//
//if (age.isEmpty()) {
//    binding.edtUsername.error = InputFormActivity.FIELD_REQUIRED
//    return
//}
//
//// Nutrients Data
//var lemak = 0F
//var kalori = 0F
//var kolestrol = 0F
//var protein = 0F
//var karbohidrat = 0F
//var sodium = 0F
//
//val intAge = age.toInt()
//
//if (gender == "Male" && intAge >= 19 && intAge <= 20) {
//    lemak = 75F
//    kalori = 2650F
//    kolestrol = 299F
//    protein = 65F
//    karbohidrat = 430F
//    sodium = 1500F
//}
//
//saveDataToPref(username, password, intAge, gender, lemak, kalori, kolestrol, protein, karbohidrat, sodium)
//
//Intent(this@InputFormActivity, DashboardActivity::class.java).also {
//    startActivity(it)
//}