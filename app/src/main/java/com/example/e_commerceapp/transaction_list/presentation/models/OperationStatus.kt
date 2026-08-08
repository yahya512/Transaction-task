package com.example.e_commerceapp.transaction_list.presentation.models

enum class OperationStatus(val status: String) {
    PENDING("Pending"),
    SUCCESS("Success"),
    FAILED("Failed"),
    REFUNDED("Refunded")
}