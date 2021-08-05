package id.develo.ailiv.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.develo.ailiv.R
import id.develo.ailiv.ui.authentication.LoginActivity
import id.develo.ailiv.ui.authentication.RegisterActivity

class SplashScreenActivity : AppCompatActivity() {

    private var timeOut:Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }
}