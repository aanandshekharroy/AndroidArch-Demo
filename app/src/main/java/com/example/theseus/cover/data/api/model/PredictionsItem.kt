package com.example.theseus.cover.data.api.model

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class PredictionsItem(

	@field:SerializedName("reference")
	val reference: String? = null,

	@field:SerializedName("types")
	val types: List<String?>? = null,

	@field:SerializedName("matched_substrings")
	val matchedSubstrings: List<MatchedSubstringsItem?>? = null,

	@field:SerializedName("terms")
	val terms: List<TermsItem?>? = null,

	@field:SerializedName("structured_formatting")
	val structuredFormatting: StructuredFormatting,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("place_id")
	val placeId: String? = null
)