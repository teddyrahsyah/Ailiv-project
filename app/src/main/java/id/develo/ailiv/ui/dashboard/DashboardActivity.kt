package id.develo.ailiv.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import id.develo.ailiv.R
import id.develo.ailiv.databinding.ActivityDashboardBinding
import id.develo.ailiv.ui.InputFormActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_logout -> {
                clearPreferences()
                intentToFormActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun intentToFormActivity() {
        Intent(this@DashboardActivity, InputFormActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    private fun clearPreferences() {
        val preferences = getSharedPreferences("nutrient_pref", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}