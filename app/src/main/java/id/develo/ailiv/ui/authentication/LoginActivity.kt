package id.develo.ailiv.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import id.develo.ailiv.R
import id.develo.ailiv.data.source.local.NutrientPreference
import id.develo.ailiv.data.source.local.entity.UserModelEntity
import id.develo.ailiv.databinding.ActivityLoginBinding
import id.develo.ailiv.ui.dashboard.DashboardActivity

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
    }

    private lateinit var binding: ActivityLoginBinding

    private lateinit var mNutrientPreference: NutrientPreference
    private lateinit var userModel: UserModelEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.brand_logo)
            .into(binding.ivBrandLogo)

        mNutrientPreference = NutrientPreference(this)

        binding.btnSignup.setOnClickListener{
            Intent(this@LoginActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnLogin.setOnClickListener {
            userModel = mNutrientPreference.getUserData()
            Log.d("TEST", userModel.username.toString())
            Log.d("TEST", userModel.password.toString())

            val username = binding.edtUsername.editText?.text.toString().trim()
            val password = binding.edtPassword.editText?.text.toString().trim()

            // Form Validation
            if (username.isEmpty()) {
                binding.edtUsername.error = FIELD_REQUIRED
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = FIELD_REQUIRED
                return@setOnClickListener
            }

            val invalidMessage = "Invalid Username or Password"
            if (username != userModel.username || password != userModel.password) {
                Toast.makeText(this, invalidMessage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (username == userModel.username && password == userModel.password) {
                mNutrientPreference.setLoginState(true)
                Intent(this@LoginActivity, DashboardActivity::class.java).also {
                    startActivity(it)
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        if (mNutrientPreference.getLoginState()) {
            Intent(this@LoginActivity, DashboardActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}