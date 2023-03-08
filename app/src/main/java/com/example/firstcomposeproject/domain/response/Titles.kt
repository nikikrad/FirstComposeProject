package com.example.firstcomposeproject.domain.response

import com.google.gson.annotations.SerializedName

data class Titles (

	@SerializedName("en")
	val en : String,
	val en_jp: String,
	@SerializedName("ja_jp")
	val ja_jp : String
)