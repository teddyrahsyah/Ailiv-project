package id.develo.ailiv.ui.dashboard

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.develo.ailiv.data.source.local.entity.NutritionEntity
import id.develo.ailiv.databinding.ItemNutritionListBinding
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class NutritionAdapter(private val listDailyNutrient: ArrayList<Float>) : RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder>() {

    private var listNutrition = ArrayList<NutritionEntity>()

    fun setNutrition(nutrition: List<NutritionEntity>?) {
        if (nutrition == null) return
        this.listNutrition.clear()
        this.listNutrition.addAll(nutrition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val itemNutritionListBinding =
            ItemNutritionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionViewHolder(itemNutritionListBinding)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        val nutrition = listNutrition[position]
        val dailyNutrient = listDailyNutrient[position]
        holder.bind(nutrition, dailyNutrient)
    }

    override fun getItemCount(): Int = listDailyNutrient.size

    inner class NutritionViewHolder(private val binding: ItemNutritionListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(nutrition: NutritionEntity, dailyNutrient: Float) {
            with(binding) {
                tvName.text = nutrition.name
                val nutritionCalculation = ((nutrition.value / dailyNutrient) * 100)
                tvValue.text = nutritionCalculation.roundToInt().toString() + "%"

//                Log.d("TEST AGAIN", rounded.toString())

                progressBar.apply {
                    progressMax = 100F
                    setProgressWithAnimation((nutrition.value.toFloat() / dailyNutrient) * 100, 1500)
                    progressBarWidth = 5F
                    backgroundProgressBarWidth = 10F
                    progressBarColor = Color.GREEN
                }
            }
        }
    }
}
