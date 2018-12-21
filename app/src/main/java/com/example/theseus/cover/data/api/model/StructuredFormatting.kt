package com.example.theseus.cover.data.api.model

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class StructuredFormatting(

	@field:SerializedName("main_text_matched_substrings")
	val mainTextMatchedSubstrings: List<MainTextMatchedSubstringsItem>? = null,

	@field:SerializedName("secondary_text")
	val secondaryText: String,

	@field:SerializedName("main_text")
	val mainText: String
)