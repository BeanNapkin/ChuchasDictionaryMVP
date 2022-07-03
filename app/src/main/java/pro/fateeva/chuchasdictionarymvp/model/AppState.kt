package pro.fateeva.chuchasdictionarymvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import pro.fateeva.chuchasdictionarymvp.room.WordEntity

sealed class AppState : Parcelable {
    @Parcelize
    data class Success(val data: List<WordEntity>?) : AppState()
    @Parcelize
    data class Error(val error: Throwable) : AppState()
    @Parcelize
    data class Loading(val progress: Int?) : AppState()
}