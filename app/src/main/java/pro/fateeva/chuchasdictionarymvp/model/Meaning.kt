package pro.fateeva.chuchasdictionarymvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meaning (
    @field:SerializedName("translation") val translation: Translation? = null
): Parcelable