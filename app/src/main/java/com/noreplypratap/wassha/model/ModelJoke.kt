package com.noreplypratap.wassha.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.noreplypratap.wassha.data.local.FlagsConverter

@Entity(tableName = "JokesTable")
@TypeConverters(FlagsConverter::class)
data class ModelJoke(
    @PrimaryKey(autoGenerate = true) val uid: Int? = 0 ,
    @ColumnInfo(name = "_id") val id: Int?,
    @ColumnInfo(name = "_category") val category: String?,
    @ColumnInfo(name = "_error") val error: Boolean?,
    @ColumnInfo(name = "_joke") val joke: String?,
    @ColumnInfo(name = "_lang") val lang: String?,
    @ColumnInfo(name = "_safe") val safe: Boolean?,
    @ColumnInfo(name = "_type") val type: String?,
    @ColumnInfo(name = "_flags")val flags: Flags?
)