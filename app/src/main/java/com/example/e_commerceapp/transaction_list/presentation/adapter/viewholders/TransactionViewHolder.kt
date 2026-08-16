package com.example.e_commerceapp.transaction_list.presentation.adapter.viewholders

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.TransactionItemBinding
import com.example.e_commerceapp.transaction_list.presentation.models.OperationStatus
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionOnClick
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule

class TransactionViewHolder(
    val binding: TransactionItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTransaction(transactionItem: TransactionUiModule, transaction: TransactionOnClick) {
        binding.apply {
            phoneNumber.text = transactionItem.reference
            nameOfTransaction.text = transactionItem.title
            costOfTransaction.text = transactionItem.amount_label
            transactionStatus.text = transactionItem.status_label
            Glide.with(itemView.context)
                .load(transactionItem.icon)
                .placeholder(R.drawable.vector19)
                .into(imageViewCard)
            cardItem.setOnClickListener {
                transaction.onClick(transactionItem)
            }

            val backGroundColor = when (transactionItem.status_label) {
                OperationStatus.SUCCESS.status -> R.color.light_green
                OperationStatus.PENDING.status -> R.color.light_orange
                OperationStatus.FAILED.status -> R.color.light_red
                OperationStatus.REFUNDED.status -> R.color.light_purple
                else -> {
                    R.color.light_orange
                }

            }

            val drawable = transactionStatus.background.mutate() as GradientDrawable
            drawable.setColor(
                ContextCompat.getColor(itemView.context, backGroundColor)
            )
            val textColor = when (transactionItem.status_label) {
                OperationStatus.SUCCESS.status -> R.color.green
                OperationStatus.PENDING.status -> R.color.orange
                OperationStatus.FAILED.status -> R.color.red
                OperationStatus.REFUNDED.status -> R.color.purple
                else -> {
                    R.color.orange
                }
            }

            transactionStatus.setTextColor(
                ContextCompat.getColor(itemView.context, textColor)
            )


        }
    }
}