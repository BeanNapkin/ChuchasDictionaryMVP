package pro.fateeva.chuchasdictionarymvp.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.fateeva.chuchasdictionarymvp.datasource.Api
import pro.fateeva.chuchasdictionarymvp.datasource.BaseInterceptor
import pro.fateeva.chuchasdictionarymvp.datasource.DataSource
import pro.fateeva.chuchasdictionarymvp.model.Word
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit : DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return getService(BaseInterceptor.interceptor).search(word)
    }

    private fun getService(interceptor: Interceptor): Api {
        return createRetrofit(interceptor).create(Api::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}