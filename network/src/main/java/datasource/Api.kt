package datasource

import pro.fateeva.chuchasdictionarymvp.model.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<Word>
}