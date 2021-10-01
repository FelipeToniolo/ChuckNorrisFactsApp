package br.ftoniolo.chucknorrisfacts.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.ftoniolo.chucknorrisfacts.databinding.ItemRecyclerCategoryItensBinding

class CategoriesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CategoriesViewHolder(var binding: ItemRecyclerCategoryItensBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerCategoryItensBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = differ.currentList[position]
        val itemViewHolder = holder as CategoriesViewHolder
        itemViewHolder.binding.apply {
            categoryItem.text = category
            categoryItem.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(category)
                }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}