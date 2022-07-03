package pro.fateeva.chuchasdictionarymvp.repository

import pro.fateeva.chuchasdictionarymvp.datasource.DataSource
import model.Word

class RepositoryImpl(private val dataSource: DataSource<List<model.Word>>) : Repository<List<model.Word>> {

    override suspend fun getData(word: String): List<model.Word> {
        return dataSource.getData(word)
    }
}