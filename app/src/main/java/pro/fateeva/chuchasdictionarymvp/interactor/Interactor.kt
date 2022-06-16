package pro.fateeva.chuchasdictionarymvp.interactor

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String): Observable<T>
}