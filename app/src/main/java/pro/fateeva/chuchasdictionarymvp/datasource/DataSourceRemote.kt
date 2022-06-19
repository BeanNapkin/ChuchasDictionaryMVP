package pro.fateeva.chuchasdictionarymvp.datasource

import io.reactivex.Observable
import pro.fateeva.chuchasdictionarymvp.model.Word
import retrofit2.Retrofit

class DataSourceRemote(
    private val retrofit: Retrofit
) : DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return getService().search(word)
    }

    private fun getService(): Api {
        return retrofit.create(Api::class.java)
    }
}