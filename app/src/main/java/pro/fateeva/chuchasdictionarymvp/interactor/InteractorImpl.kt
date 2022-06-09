package pro.fateeva.chuchasdictionarymvp.interactor

import io.reactivex.Observable
import pro.fateeva.chuchasdictionarymvp.AppState
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.repository.Repository

class InteractorImpl(
    private val remoteRepository: Repository<List<Word>>
) : Interactor<AppState> {

    override fun getData(word: String): Observable<AppState> {
       return remoteRepository.getData(word).map { AppState.Success(it) }
    }

}