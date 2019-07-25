package ru.hetfieldan24.otuscoroutines.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SongsTable::class], version = 3, exportSchema = false)
abstract class SongsDatabase: RoomDatabase()
{
    abstract val songsDao: SongsDao

    companion object
    {
        @Volatile
        private var INSTANCE: SongsDatabase? = null

        fun getInstance(context: Context): SongsDatabase
        {
            synchronized(this)
            {
                var instance = INSTANCE

                if (instance == null)
                {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SongsDatabase::class.java,
                            "songs_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}