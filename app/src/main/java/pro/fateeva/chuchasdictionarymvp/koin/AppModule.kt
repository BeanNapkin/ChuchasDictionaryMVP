package pro.fateeva.chuchasdictionarymvp.koin

import androidx.room.Room
import model.WordDTO
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pro.fateeva.chuchasdictionarymvp.app
import pro.fateeva.chuchasdictionarymvp.repository.Repository
import pro.fateeva.chuchasdictionarymvp.repository.RepositoryImpl
import pro.fateeva.chuchasdictionarymvp.room.WordDataBase
import pro.fateeva.chuchasdictionarymvp.usecase.HistoryUseCase
import pro.fateeva.chuchasdictionarymvp.usecase.SearchWordUseCase
import pro.fateeva.chuchasdictionarymvp.view.history.HistoryViewModel
import pro.fateeva.chuchasdictionarymvp.view.listofwords.ListOfWordsViewModel

object AppModule {
    private const val URL = "https://dictionary.skyeng.ru/api/public/v1/"

    val mainModule = module{
        single<SearchWordUseCase>{ SearchWordUseCase(get()) }

        single<HistoryUseCase>{ HistoryUseCase(get()) }

        single<Repository<List<WordDTO>>> { RepositoryImpl(get()) }

        single<WordDataBase>{
            Room.databaseBuilder(androidApplication().app, WordDataBase::class.java, "word_database").build()
        }
        single { get<WordDataBase>().WordDao() }

        viewModel<ListOfWordsViewModel>{ ListOfWordsViewModel(get(), get(), get()) }

        viewModel<HistoryViewModel>{ HistoryViewModel(get()) }
    }
}