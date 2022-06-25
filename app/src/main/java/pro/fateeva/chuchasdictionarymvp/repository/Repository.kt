package pro.fateeva.chuchasdictionarymvp.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}