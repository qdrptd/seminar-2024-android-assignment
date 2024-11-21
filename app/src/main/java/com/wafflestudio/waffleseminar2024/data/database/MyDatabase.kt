package com.wafflestudio.waffleseminar2024.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MyEntity::class, LikedMovie::class], version = 2)
@TypeConverters(MyConverters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase ?= null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "example_database"
                )
                    .createFromAsset("database/prepopulated_db.db") // 이 부분을 추가합니다.
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance

                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE liked_movies (" +
                            "id INTEGER PRIMARY KEY NOT NULL, " +
                            "poster_path TEXT NOT NULL)"
                )
            }
        }
    }
}