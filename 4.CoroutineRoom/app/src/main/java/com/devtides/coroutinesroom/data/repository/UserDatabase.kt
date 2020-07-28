package com.devtides.coroutinesroom.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.data.model.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDatabase? = null

        private val DATABASE_NAME = "userDatabase"

        operator fun invoke(context: Context) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        ).also {
                            instance = it
                        }
                }

        private fun buildDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}