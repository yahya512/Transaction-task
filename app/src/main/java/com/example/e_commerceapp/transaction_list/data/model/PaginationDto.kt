package com.example.e_commerceapp.transaction_list.data.model

import com.google.gson.annotations.SerializedName

data class PaginationDto(
    @SerializedName("current_page") val currentPage: Int?,
    @SerializedName("per_page") val perPage: Int?,
    @SerializedName("last_page") val lastPage: Int?,
    @SerializedName("has_more") val hasMore: Boolean?,
    @SerializedName("total") val total: Int?,
    @SerializedName("count") val count: Int?
)
