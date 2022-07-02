package pro.fateeva.chuchasdictionarymvp.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.fateeva.chuchasdictionarymvp.usecase.HistoryUseCase
import pro.fateeva.chuchasdictionarymvp.room.WordEntity

class HistoryViewModel(
    private val historyUseCase: HistoryUseCase
) : ViewModel(){

    private val scope = CoroutineScope(Dispatchers.IO)

    private val mutableLiveData: MutableLiveData<List<WordEntity>> = MutableLiveData()
    val wordsLiveData: LiveData<List<WordEntity>> = mutableLiveData

    fun getAllWords(){
        scope.launch { mutableLiveData.postValue(historyUseCase.getAllWords()) }
    }
}