package id.develo.ailiv.ui.warning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.develo.ailiv.R
import id.develo.ailiv.data.source.local.entity.WarningEntity
import id.develo.ailiv.databinding.ActivityWarningBinding
import id.develo.ailiv.viewmodel.ViewModelFactory

class WarningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWarningBinding
    private lateinit var listWarning: ArrayList<WarningEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWarningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get Data from Dashboard (Nutrtion Over)
        val warningData = WarningActivityArgs.fromBundle(intent.extras as Bundle).warningData
        warningData.toList()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[WarningViewModel::class.java]

        listWarning = ArrayList()
        for (i in warningData.indices) {
            val filename = warningData[i] + ".json"
            val data = viewModel.getWarning(filename)
            listWarning.add(data)
            Log.d("DATA 1", data.name.toString())
            Log.d("DATA 2", data.warningMessage.toString())
        }

        val warningAdapter = WarningAdapter()
        warningAdapter.setWarning(listWarning)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@WarningActivity)
            setHasFixedSize(true)
            adapter = warningAdapter
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