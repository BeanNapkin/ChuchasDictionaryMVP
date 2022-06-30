package pro.fateeva.chuchasdictionarymvp.usecase

import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.repository.Repository

class SearchWordUseCase(
    private val remoteRepository: Repository<List<Word>>
) {
    suspend fun searchWord(word: String): AppState {
        return remoteRepository.getData(word).let { AppState.Success(it) }
    }
}