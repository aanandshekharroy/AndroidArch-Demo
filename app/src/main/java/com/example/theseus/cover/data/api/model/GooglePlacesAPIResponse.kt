package com.example.theseus.cover.data.api.model

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class GooglePlacesAPIResponse(

	@field:SerializedName("predictions")
	val predictions: List<PredictionsItem>,

	@field:SerializedName("status")
	val status: String? = null
)