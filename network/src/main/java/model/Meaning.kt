package model

import com.google.gson.annotations.SerializedName

data class Meaning (
    @field:SerializedName("translation") val translation: Translation? = null,
    @field:SerializedName("imageUrl") val imageUrl: String? = null
)