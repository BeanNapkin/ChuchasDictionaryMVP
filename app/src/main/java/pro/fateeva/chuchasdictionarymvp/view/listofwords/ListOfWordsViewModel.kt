package pro.fateeva.chuchasdictionarymvp.view.listofwords

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.usecase.ListOfWordsUseCase

class ListOfWordsViewModel(
    private val state: SavedStateHandle,
    private val useCase: ListOfWordsUseCase
) : ViewModel() {

    val wordLiveData: LiveData<AppState> = state.getLiveData("word")

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        Log.d("ViewModel", "View model is created")
    }

    fun getData(word: String) = scope.launch {
        val liveData = state.getLiveData<AppState>("word")
        liveData.postValue(AppState.Loading(0))
        try {
            liveData.postValue(useCase.getData(word))
        } catch (e: Throwable) {
            liveData.postValue(AppState.Error(e))
        }
    }
}