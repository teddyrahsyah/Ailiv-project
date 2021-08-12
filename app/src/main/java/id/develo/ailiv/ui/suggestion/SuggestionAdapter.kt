package id.develo.ailiv.ui.suggestion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.develo.ailiv.data.source.local.entity.SuggestionEntity
import id.develo.ailiv.databinding.ListSuggestionItemBinding

class SuggestionAdapter : RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>() {

    private val listSuggestion = ArrayList<SuggestionEntity>()

    fun setSuggestion(suggestion: List<SuggestionEntity>?) {
        if (suggestion == null) return
        this.listSuggestion.clear()
        this.listSuggestion.addAll(suggestion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val itemListBinding = ListSuggestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestionViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        val suggestion = listSuggestion[position]
        holder.bind(suggestion)
    }

    override fun getItemCount(): Int = listSuggestion.size

    inner class SuggestionViewHolder(private val binding: ListSuggestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(suggestion: SuggestionEntity) {
                with(binding) {
                    tvName.text = suggestion.name
                    tvValue.text = suggestion.value.toString()
                    tvUnit.text = suggestion.unit
                }
            }
    }
}