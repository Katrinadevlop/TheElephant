package com.example.theelephant.Model.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theelephant.Model.DataBase.DAO.ParentDAO
import com.example.theelephant.Model.DataBase.DAO.SpecialistDAO
import com.example.theelephant.Model.DataBase.Entities.ParentEntity
import com.example.theelephant.Model.DataBase.Entities.SpecialistEntity

@Database(
    entities = [ParentEntity::class, SpecialistEntity::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract fun ParentDao(): ParentDAO
    abstract fun SpecialistDao(): SpecialistDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDataBase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}