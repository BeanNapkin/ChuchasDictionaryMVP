package datasource

import model.WordDTO
import retrofit2.Retrofit

class DataSourceRemote(
    private val retrofit: Retrofit
) : DataSource<List<WordDTO>> {

    override suspend fun getData(word: String): List<WordDTO> {
        return getService().search(word)
    }

    private fun getService(): Api {
        return retrofit.create(Api::class.java)
    }
}