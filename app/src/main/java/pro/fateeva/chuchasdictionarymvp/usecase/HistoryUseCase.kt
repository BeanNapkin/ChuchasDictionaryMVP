package pro.fateeva.chuchasdictionarymvp.usecase

import pro.fateeva.chuchasdictionarymvp.room.WordDataBase
import pro.fateeva.chuchasdictionarymvp.room.WordEntity

class HistoryUseCase(
    private val dataBase: WordDataBase
) {
    fun saveWord(word: WordEntity) {
        dataBase.WordDao().insertWord(word)
    }

    fun getAllWords() : List<WordEntity>{
        return dataBase.WordDao().getAllWords()
    }
}