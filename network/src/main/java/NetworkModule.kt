import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import datasource.BaseInterceptor
import datasource.DataSource
import datasource.DataSourceRemote
import model.WordDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val URL = "https://dictionary.skyeng.ru/api/public/v1/"

    val mainModule = module{

        single<DataSource<List<WordDTO>>>{ DataSourceRemote(get()) }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
        }

        single<OkHttpClient>{
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(BaseInterceptor.interceptor)
            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            httpClient.build()
        }
    }
}