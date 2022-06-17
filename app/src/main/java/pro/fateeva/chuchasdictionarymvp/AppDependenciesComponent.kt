package pro.fateeva.chuchasdictionarymvp

import dagger.Component
import pro.fateeva.chuchasdictionarymvp.view.MainActivity
import pro.fateeva.chuchasdictionarymvp.view.listofwords.ListOfWordsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ AppDependenciesModule::class])
interface AppDependenciesComponent {
    fun inject (listOfWordsViewModel: ListOfWordsViewModel)
}