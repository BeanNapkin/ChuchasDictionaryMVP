package pro.fateeva.chuchasdictionarymvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class AppState : Parcelable {
    @Parcelize
    data class Success(val data: List<Word>?) : AppState()
    @Parcelize
    data class Error(val error: Throwable) : AppState()
    @Parcelize
    data class Loading(val progress: Int?) : AppState()
}