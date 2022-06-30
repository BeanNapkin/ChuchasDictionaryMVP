package pro.fateeva.chuchasdictionarymvp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getAllWords(): List<WordEntity>

    @Insert
    fun insertWord(word: WordEntity)
}