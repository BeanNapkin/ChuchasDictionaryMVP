package pro.fateeva.chuchasdictionarymvp.view.listofwords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import pro.fateeva.chuchasdictionarymvp.usecase.ListOfWordsUseCase
import pro.fateeva.chuchasdictionarymvp.datasource.DataSourceRemote
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.repository.RepositoryImpl
import pro.fateeva.chuchasdictionarymvp.rx.SchedulerProvider

class ListOfWordsViewModel : ViewModel() {

    private val useCase: ListOfWordsUseCase = ListOfWordsUseCase(RepositoryImpl(DataSourceRemote()))
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    private val mutableLiveData: MutableLiveData<AppState> = MutableLiveData()
    val wordLiveData: LiveData<AppState> = mutableLiveData

    fun getData(word: String) {
        compositeDisposable.add(
            useCase.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { mutableLiveData.postValue(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                mutableLiveData.postValue(appState)
            }

            override fun onError(e: Throwable) {
                mutableLiveData.postValue(AppState.Error(e))
            }

            override fun onComplete() {}
        }
    }
}