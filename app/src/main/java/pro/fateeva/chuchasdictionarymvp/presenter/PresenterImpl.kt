package pro.fateeva.chuchasdictionarymvp.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import pro.fateeva.chuchasdictionarymvp.datasource.DataSourceRemote
import pro.fateeva.chuchasdictionarymvp.interactor.InteractorImpl
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.repository.RepositoryImpl
import pro.fateeva.chuchasdictionarymvp.rx.SchedulerProvider
import pro.fateeva.chuchasdictionarymvp.view.FragmentView
import java.lang.RuntimeException

class PresenterImpl<A: AppState, V: FragmentView>(
    private val interactor: InteractorImpl = InteractorImpl(RepositoryImpl(DataSourceRemote())),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<A, V> {
    private var currentFragment: V? = null

    override fun attachFragment(view: V) {
        if (view != currentFragment) {
            currentFragment = view
        }
    }

    override fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentFragment?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentFragment?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentFragment?.renderData(AppState.Error(e))
            }

            override fun onComplete() {}
        }
    }

    override fun detachFragment(view: V) {
        compositeDisposable.clear()
        if (view == currentFragment) {
            currentFragment = null
        }
    }
}