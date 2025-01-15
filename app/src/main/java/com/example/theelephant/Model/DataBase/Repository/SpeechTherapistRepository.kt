package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist

class SpeechTherapistRepository(private val dataBase: DataBase) {
    private val speechTherapistDao = dataBase.SpeechTherapistDao()

    suspend fun getSpeechTherapist() {
        speechTherapistDao.getSpeechTherapist()
    }

    suspend fun setSpeechTherapist(id: Int) {
        speechTherapistDao.setSpeechTherapist(id)
    }

    suspend fun deleteSpeechTherapist(id: Int) {
        speechTherapistDao.deleteSpeechTherapist(id)
    }

    suspend fun deleteAllSpeechTherapist() {
        speechTherapistDao.deleteAllSpeechTherapist()
    }

    suspend fun insertSpeechTherapist(speechTherapist: SpeechTherapist) {
        speechTherapistDao.insertSpeechTherapist(speechTherapist)
    }
}