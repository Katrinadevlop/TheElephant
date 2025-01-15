package com.example.theelephant.Model.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theelephant.Model.DataBase.Entities.MassageTherapist
import com.example.theelephant.Model.DataBase.Entities.Neuropsychologist
import com.example.theelephant.Model.DataBase.Entities.Parent
import com.example.theelephant.Model.DataBase.Entities.Psychologist
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist
import com.example.theelephant.Model.DataBase.Entities.Tomatis
import com.example.theelephant.Model.DataBase.Entities.Тeurodefectologist

@Database(entities = [MassageTherapist::class, Neuropsychologist::class, Parent::class,
                     Psychologist::class, SpeechTherapist::class, Tomatis::class, Тeurodefectologist::class], version = 1)
abstract class DataBase:RoomDatabase(){
    abstract fun parentDao():ParentDAO
    abstract fun MassageTherapistDao():MassageTherapistDAO
    abstract fun NeuropsychologistDao():NeuropsychologistDAO
    abstract fun PsychologistDao():PsychologistDAO
    abstract fun SpeechTherapistDao():SpeechTherapistDAO
    abstract fun TomatisDao():TomatisDAO
    abstract fun ТeurodefectologistDao():ТeurodefectologistDAO

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null
    }

    fun getDataBase(context:Context): DataBase{
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