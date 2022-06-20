package pro.fateeva.chuchasdictionarymvp.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}