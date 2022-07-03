package pro.fateeva.chuchasdictionarymvp.repository

import datasource.DataSource
import model.WordDTO

class RepositoryImpl(private val dataSource: DataSource<List<WordDTO>>) : Repository<List<WordDTO>> {

    override suspend fun getData(word: String): List<WordDTO> {
        return dataSource.getData(word)
    }
}