package pro.fateeva.chuchasdictionarymvp.view.listofwords

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import pro.fateeva.chuchasdictionarymvp.usecase.ListOfWordsUseCase
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.rx.SchedulerProvider
import javax.inject.Inject

class ListOfWordsViewModel(private val state: SavedStateHandle) : ViewModel() {

    @Inject
    lateinit var useCase: ListOfWordsUseCase
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    val wordLiveData: LiveData<AppState> = state.getLiveData("word")

    init {
        Log.d("ViewModel", "View model is created")
    }

    fun getData(word: String) {
        compositeDisposable.add(
            useCase.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { state["word"] = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                state["word"] = appState
            }

            override fun onError(e: Throwable) {
               state["word"] = AppState.Error(e)
            }

            override fun onComplete() {}
        }
    }
}