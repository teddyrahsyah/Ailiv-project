package id.develo.ailiv.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.develo.ailiv.R
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.data.source.local.entity.NutrientThreshold
import id.develo.ailiv.data.source.local.entity.UserModelEntity
import id.develo.ailiv.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_AGE_RANGE = "Khusus usia 19 - 64 tahun"
    }

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var mNutrientPreference: NutrientPreference
    private lateinit var userModel: UserModelEntity
    private lateinit var threshold: NutrientThreshold

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.brand_logo)
            .into(binding.ivBrandLogo)

        mNutrientPreference = NutrientPreference(this)
        userModel = UserModelEntity()
        threshold = NutrientThreshold()

        saveUserData()
    }

    private fun saveUserData() {
        binding.btnRegister.setOnClickListener {
            // User Data
            val username = binding.edtUsername.editText?.text.toString().trim()
            val password = binding.edtPassword.editText?.text.toString().trim()

            val age = binding.edtAge.editText?.text.toString()

            val radioGroup = binding.radioGroup.checkedRadioButtonId
            val gender: String?

            // Form Validation
            if (username.isEmpty()) {
                binding.edtUsername.error = FIELD_REQUIRED
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.edtPassword.error = FIELD_REQUIRED
                return@setOnClickListener
            }
            if (age.isEmpty()) {
                binding.edtAge.error = FIELD_REQUIRED
                return@setOnClickListener
            }

            val intAge = age.toInt()

            if (intAge < 19) {
                binding.edtAge.error = FIELD_AGE_RANGE
                return@setOnClickListener
            }
            if (intAge > 64) {
                binding.edtAge.error = FIELD_AGE_RANGE
                return@setOnClickListener
            }
            if (binding.radMale.isChecked || binding.radFemale.isChecked) {
                val selectedGender = findViewById<RadioButton>(radioGroup)
                gender = selectedGender.text.toString()
            } else {
                Toast.makeText(this, "Please Select Gender First!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Nutrients Data Initialization
            var lemak = 0F
            var kalori = 0F
            var kolestrol = 0F
            var protein = 0F
            var karbohidrat = 0F
            var sodium = 0F

            if (gender == "Male" && intAge >= 19 && intAge <= 29) {
                lemak = 75F
                kalori = 2650F
                kolestrol = 299F
                protein = 65F
                karbohidrat = 430F
                sodium = 1500F
            } else if (gender == "Male" && intAge >= 30 && intAge <= 49) {
                lemak = 70F
                kalori = 2650F
                kolestrol = 299F
                protein = 65F
                karbohidrat = 415F
                sodium = 1500F
            } else if (gender == "Male" && intAge >= 50 && intAge <= 64) {
                lemak = 60F
                kalori = 2150F
                kolestrol = 299F
                protein = 65F
                karbohidrat = 340F
                sodium = 1500F
            } else if (gender == "Female" && intAge >= 19 && intAge <= 20) {
                lemak = 65F
                kalori = 2250F
                kolestrol = 299F
                protein = 60F
                karbohidrat = 360F
                sodium = 1500F
            } else if (gender == "Female" && intAge >= 30 && intAge <= 49) {
                lemak = 60F
                kalori = 2150F
                kolestrol = 299F
                protein = 60F
                karbohidrat = 340F
                sodium = 1500F
            } else if (gender == "Female" && intAge >= 50 && intAge <= 64) {
                lemak = 50F
                kalori = 1800F
                kolestrol = 299F
                protein = 65F
                karbohidrat = 280F
                sodium = 1400F
            }

            mNutrientPreference.clearPreference()

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

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            Intent(this@RegisterActivity, LoginActivity::class.java).also {
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
        userModel.username = username
        userModel.password = password
        userModel.age = age
        userModel.gender = gender
        userModel.isLogin = false

        threshold.lemak = lemak
        threshold.kalori = kalori
        threshold.kolestrol = kolestrol
        threshold.protein = protein
        threshold.karbohidrat = karbohidrat
        threshold.sodium = sodium

        mNutrientPreference.setUserDailyThreshold(userModel, threshold)
    }
}
