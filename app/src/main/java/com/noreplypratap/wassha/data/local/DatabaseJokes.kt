package com.noreplypratap.wassha.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noreplypratap.wassha.model.ModelJoke

@Database(entities = [ModelJoke::class], version = 1, exportSchema = false)
@TypeConverters(FlagsConverter::class)
abstract class DatabaseJokes : RoomDatabase() {

    abstract fun getJokesDAO() : JokesDAO

    companion object {
        @Volatile
        private var INSTANCE: DatabaseJokes? = null

        fun createDatabaseINSTANCE(context: Context): DatabaseJokes {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseJokes::class.java,
                        "DatabaseJokes"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}