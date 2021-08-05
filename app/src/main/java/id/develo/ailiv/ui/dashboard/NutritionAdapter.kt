package id.develo.ailiv.ui.dashboard

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.develo.ailiv.data.source.local.entity.AccumulatedNutrient
import id.develo.ailiv.databinding.ItemNutritionListBinding
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class NutritionAdapter : RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder>() {

    private var listNutrition = ArrayList<AccumulatedNutrient>()

    fun setNutrition(nutrition: List<AccumulatedNutrient>?) {
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
        holder.bind(nutrition)
    }

    override fun getItemCount(): Int = listNutrition.size

    inner class NutritionViewHolder(private val binding: ItemNutritionListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(nutrition: AccumulatedNutrient) {
            with(binding) {
                tvName.text = nutrition.name
                tvValue.text = nutrition.accumulatedValue.roundToInt().toString() + "%"

                progressBar.apply {
                    progressMax = 100F
                    setProgressWithAnimation(nutrition.accumulatedValue.toFloat(), 1500)
                    progressBarWidth = 5F
                    backgroundProgressBarWidth = 10F
                    progressBarColor = Color.GREEN
                }
            }
        }
    }
}
