package pro.fateeva.chuchasdictionarymvp.presenter

import pro.fateeva.chuchasdictionarymvp.AppState
import pro.fateeva.chuchasdictionarymvp.view.FragmentView

interface Presenter<A: AppState, V: FragmentView> {
    fun getData(word: String)
    fun attachFragment(view: V)
    fun detachFragment(view: V)
}