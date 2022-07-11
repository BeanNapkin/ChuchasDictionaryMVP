package pro.fateeva.chuchasdictionarymvp.koin

import androidx.room.Room
import model.WordDTO
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pro.fateeva.chuchasdictionarymvp.app
import pro.fateeva.chuchasdictionarymvp.repository.Repository
import pro.fateeva.chuchasdictionarymvp.repository.RepositoryImpl
import pro.fateeva.chuchasdictionarymvp.room.WordDataBase
import pro.fateeva.chuchasdictionarymvp.usecase.HistoryUseCase
import pro.fateeva.chuchasdictionarymvp.usecase.SearchWordUseCase
import pro.fateeva.chuchasdictionarymvp.view.history.HistoryFragment
import pro.fateeva.chuchasdictionarymvp.view.history.HistoryViewModel
import pro.fateeva.chuchasdictionarymvp.view.listofwords.ListOfWordsFragment
import pro.fateeva.chuchasdictionarymvp.view.listofwords.ListOfWordsViewModel

object AppModule {
    val mainModule = module{
        single<HistoryUseCase>{ HistoryUseCase(get()) }

        single<WordDataBase>{
            Room.databaseBuilder(androidApplication().app, WordDataBase::class.java, "word_database").build()
        }

        scope(named<ListOfWordsFragment>()){
            scoped<SearchWordUseCase>{ SearchWordUseCase(get()) }
            scoped<Repository<List<WordDTO>>> { RepositoryImpl(get()) }
            viewModel<ListOfWordsViewModel>{ params -> ListOfWordsViewModel(params.get(), get(), get()) }
        }

        scope(named<HistoryFragment>()) {
            scoped { get<WordDataBase>().WordDao() }
            viewModel<HistoryViewModel>{ HistoryViewModel(get()) }
        }
    }
}