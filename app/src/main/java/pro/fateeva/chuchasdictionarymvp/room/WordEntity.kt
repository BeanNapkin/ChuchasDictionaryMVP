package pro.fateeva.chuchasdictionarymvp.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "word") val word: String?,
    @ColumnInfo(name = "translation") val translation: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?
) : Parcelable
