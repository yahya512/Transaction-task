package com.example.e_commerceapp.transaction_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.RowItemBinding
import com.example.e_commerceapp.transaction_details.presentation.model.RowsUiModel

class SectionAdapter : ListAdapter<RowsUiModel, SectionAdapter.SectionViewHolder>(
    RowDiffUtil()
) {
    class SectionViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SectionViewHolder {
        val view = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SectionViewHolder, position: Int
    ) {
        val rowItem = getItem(position)
        holder.binding.apply {
            paymentLabelTextView.text = rowItem.label
            descriptionTv.text = rowItem.value
            dividerLine.isVisible = isLastItem(position, itemCount)
        }
    }

    private fun isLastItem(position: Int, itemCount: Int): Boolean {
        return position != itemCount - 1
    }
}