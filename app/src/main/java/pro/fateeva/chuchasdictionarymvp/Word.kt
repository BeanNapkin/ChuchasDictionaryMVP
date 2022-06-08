package pro.fateeva.chuchasdictionarymvp

import com.google.gson.annotations.SerializedName

class Word(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Translation>?
)