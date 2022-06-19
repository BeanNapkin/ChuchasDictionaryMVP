package pro.fateeva.chuchasdictionarymvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Translation(
    @field:SerializedName("text") val translation: String?
): Parcelable
