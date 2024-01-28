package com.noreplypratap.wassha.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noreplypratap.wassha.model.ModelJoke

@Dao
interface JokesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: ModelJoke)

    @Query("SELECT * FROM JokesTable ORDER BY uid DESC LIMIT 1")
    fun getLocalJoke(): ModelJoke?

    // Add More Interfaces ...

}
