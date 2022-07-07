package pro.fateeva.chuchasdictionarymvp.usecase

import model.WordDTO
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.repository.Repository
import pro.fateeva.chuchasdictionarymvp.room.WordEntity

class SearchWordUseCase(
    private val remoteRepository: Repository<List<WordDTO>>
) {
    suspend fun searchWord(word: String): AppState {
        return remoteRepository.getData(word).let { wordDTOList -> AppState.Success(wordDTOList.map {
            WordEntity(
                id = 0,
                word = it.text,
                translation = it.meanings?.first()?.translation?.translation,
                imageUrl = it.meanings?.first()?.imageUrl
            )
        })
        }
    }
}