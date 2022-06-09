package pro.fateeva.chuchasdictionarymvp.view

import pro.fateeva.chuchasdictionarymvp.AppState

interface FragmentView  {
    fun renderData(appState: AppState)
}