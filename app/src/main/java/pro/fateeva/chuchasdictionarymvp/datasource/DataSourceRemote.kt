package pro.fateeva.chuchasdictionarymvp.datasource

import io.reactivex.Observable
import pro.fateeva.chuchasdictionarymvp.model.Word

class DataSourceRemote(private val remoteProvider: Retrofit = Retrofit()) :
    DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> = remoteProvider.getData(word)
}