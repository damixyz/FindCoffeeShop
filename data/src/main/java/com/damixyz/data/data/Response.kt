package com.damixyz.data.data

data class Response(
    val groups: List<Group>,
    val headerFullLocation: String,
    val headerLocation: String,
    val headerLocationGranularity: String,
    val query: String,
    val suggestedBounds: SuggestedBounds,
    val suggestedFilters: SuggestedFilters,
    val suggestedRadius: Int,
    val totalResults: Int
)