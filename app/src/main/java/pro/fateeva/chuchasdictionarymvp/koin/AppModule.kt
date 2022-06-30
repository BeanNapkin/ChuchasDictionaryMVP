package pro.fateeva.chuchasdictionarymvp.koin

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pro.fateeva.chuchasdictionarymvp.usecase.HistoryUseCase
import pro.fateeva.chuchasdictionarymvp.room.WordDataBase
import pro.fateeva.chuchasdictionarymvp.app
import pro.fateeva.chuchasdictionarymvp.datasource.BaseInterceptor
import pro.fateeva.chuchasdictionarymvp.datasource.DataSource
import pro.fateeva.chuchasdictionarymvp.datasource.DataSourceRemote
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.repository.Repository
import pro.fateeva.chuchasdictionarymvp.repository.RepositoryImpl
import pro.fateeva.chuchasdictionarymvp.usecase.SearchWordUseCase
import pro.fateeva.chuchasdictionarymvp.view.history.HistoryViewModel
import pro.fateeva.chuchasdictionarymvp.view.listofwords.ListOfWordsViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    private const val URL = "https://dictionary.skyeng.ru/api/public/v1/"

    val mainModule = module{
        single<SearchWordUseCase>{ SearchWordUseCase(get()) }

        single<HistoryUseCase>{ HistoryUseCase(get()) }

        single<Repository<List<Word>>> { RepositoryImpl(get()) }

        single<DataSource<List<Word>>>{ DataSourceRemote(get()) }

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

        single<WordDataBase>{
            Room.databaseBuilder(androidApplication().app, WordDataBase::class.java, "word_database").build()
        }
        single { get<WordDataBase>().WordDao() }

        viewModel<ListOfWordsViewModel>{ ListOfWordsViewModel(get(), get(), get()) }

        viewModel<HistoryViewModel>{ HistoryViewModel(get()) }
    }
}