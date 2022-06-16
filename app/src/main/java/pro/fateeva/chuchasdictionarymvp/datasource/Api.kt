package pro.fateeva.chuchasdictionarymvp.datasource

import io.reactivex.Observable
import pro.fateeva.chuchasdictionarymvp.model.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<Word>>
}