package pro.fateeva.chuchasdictionarymvp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], version = 1, exportSchema = true)
abstract class WordDataBase : RoomDatabase() {
    abstract fun WordDao(): WordDao
}