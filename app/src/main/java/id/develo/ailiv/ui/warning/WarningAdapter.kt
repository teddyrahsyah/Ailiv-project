package id.develo.ailiv.ui.warning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.develo.ailiv.data.source.local.entity.WarningEntity
import id.develo.ailiv.databinding.ItemWarningListBinding

class WarningAdapter : RecyclerView.Adapter<WarningAdapter.WarningViewHolder>() {

    private val listWarning = ArrayList<WarningEntity>()

    fun setWarning(warning: List<WarningEntity>?) {
        if (warning == null) return
        this.listWarning.clear()
        this.listWarning.addAll(warning)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarningViewHolder {
        val itemWarningListBinding =
            ItemWarningListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WarningViewHolder(itemWarningListBinding)
    }

    override fun onBindViewHolder(holder: WarningViewHolder, position: Int) {
        val warning = listWarning[position]
        holder.bind(warning)
    }

    override fun getItemCount(): Int = listWarning.size

    inner class WarningViewHolder(private val binding: ItemWarningListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(warning: WarningEntity) {
            with(binding) {
                tvName.text = warning.name
                tvMessage.text = warning.warningMessage
            }
        }
    }
}
