package model

import com.google.gson.annotations.SerializedName

class WordDTO (
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meaning>?
)