package com.example.e_commerceapp.transaction_list.presentation.adapter.viewholders

enum class OperationStatus(val status: String) {
    PENDING("pending"),
    SUCCESS("success"),
    FAILED("failed"),
    REFUNDED("refunded")
}