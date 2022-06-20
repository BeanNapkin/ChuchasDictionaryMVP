package pro.fateeva.chuchasdictionarymvp.repository

import pro.fateeva.chuchasdictionarymvp.datasource.DataSource
import pro.fateeva.chuchasdictionarymvp.model.Word

class RepositoryImpl(private val dataSource: DataSource<List<Word>>) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}