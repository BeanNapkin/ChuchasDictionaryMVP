package pro.fateeva.chuchasdictionarymvp.repository

import io.reactivex.Observable
import pro.fateeva.chuchasdictionarymvp.datasource.DataSource
import pro.fateeva.chuchasdictionarymvp.model.Word

class RepositoryImpl(private val dataSource: DataSource<List<Word>>) : Repository<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return dataSource.getData(word)
    }

}