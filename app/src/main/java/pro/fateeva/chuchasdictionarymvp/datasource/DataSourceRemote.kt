package pro.fateeva.chuchasdictionarymvp.datasource

import pro.fateeva.chuchasdictionarymvp.model.Word
import retrofit2.Retrofit

class DataSourceRemote(
    private val retrofit: Retrofit
) : DataSource<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return getService().search(word)
    }

    private fun getService(): Api {
        return retrofit.create(Api::class.java)
    }
}