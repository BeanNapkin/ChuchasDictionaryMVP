package pro.fateeva.chuchasdictionarymvp

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.fateeva.chuchasdictionarymvp.datasource.BaseInterceptor
import pro.fateeva.chuchasdictionarymvp.datasource.DataSource
import pro.fateeva.chuchasdictionarymvp.datasource.DataSourceRemote
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.repository.Repository
import pro.fateeva.chuchasdictionarymvp.repository.RepositoryImpl
import pro.fateeva.chuchasdictionarymvp.usecase.ListOfWordsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppDependenciesModule {

    @Singleton
    @Provides
    fun provideListOfWordsUseCase(repository: Repository<List<Word>>): ListOfWordsUseCase {
        return ListOfWordsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideRepository(dataSource: DataSource<List<Word>>): Repository<List<Word>> {
        return RepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideDataSource(retrofit: Retrofit): DataSource<List<Word>> {
        return DataSourceRemote(retrofit)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(BaseInterceptor.interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }
}