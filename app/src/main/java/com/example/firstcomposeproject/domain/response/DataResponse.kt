package com.example.firstcomposeproject.domain.response

import com.example.firstcomposeproject.domain.response.Attributes
import com.google.gson.annotations.SerializedName

data class DataResponse (

    @SerializedName("id")
	val id : Int,
    @SerializedName("attributes")
	val attributes : Attributes,
)