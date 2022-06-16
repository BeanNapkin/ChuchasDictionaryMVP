package pro.fateeva.chuchasdictionarymvp.view

import pro.fateeva.chuchasdictionarymvp.model.AppState

interface FragmentView  {
    fun renderData(appState: AppState)
}