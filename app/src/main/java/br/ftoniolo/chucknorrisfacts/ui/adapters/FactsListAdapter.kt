package br.ftoniolo.chucknorrisfacts.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.ftoniolo.chucknorrisfacts.databinding.ItemRecyclerFactsBinding
import br.ftoniolo.chucknorrisfacts.model.FactsResultResponse

class FactsListAdapter() : RecyclerView.Adapter<FactsListAdapter.FactsViewHolder>() {

    inner class FactsViewHolder(var binding: ItemRecyclerFactsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<FactsResultResponse>() {
        override fun areItemsTheSame(
            oldItem: FactsResultResponse,
            newItem: FactsResultResponse
        ): Boolean {
            return oldItem.value == newItem.value
        }

        override fun areContentsTheSame(
            oldItem: FactsResultResponse,
            newItem: FactsResultResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val binding = ItemRecyclerFactsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FactsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val searchResponse = differ.currentList[position]
        holder.binding.apply {
            if (searchResponse.categories?.isNotEmpty() == true) {
                categoryItem.text = searchResponse.categories[0]
            } else {
                categoryItem.text = "UNCATEGORY"
            }
            textItem.text = searchResponse.value ?: "I was so fast I forgot the result, do a new search"
            shareItem.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(searchResponse)
                }
            }
        }
    }


    private var onItemClickListener: ((FactsResultResponse) -> Unit)? = null

    fun setOnClickListener(listener: (FactsResultResponse) -> Unit) {
        onItemClickListener = listener
    }
}