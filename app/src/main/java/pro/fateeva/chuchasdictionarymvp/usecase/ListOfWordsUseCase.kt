package pro.fateeva.chuchasdictionarymvp.usecase

import io.reactivex.Observable
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.repository.Repository

class ListOfWordsUseCase(
    private val remoteRepository: Repository<List<Word>>
) {
    fun getData(word: String): Observable<AppState> {
        return remoteRepository.getData(word).map { AppState.Success(it) }
    }
}