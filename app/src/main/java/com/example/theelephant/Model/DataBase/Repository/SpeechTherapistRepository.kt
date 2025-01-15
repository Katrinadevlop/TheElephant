package com.example.theelephant.Model.DataBase.Repository

import com.example.theelephant.Model.DataBase.DataBase
import com.example.theelephant.Model.DataBase.Entities.SpeechTherapist
import kotlinx.coroutines.flow.Flow

class SpeechTherapistRepository(dataBase: DataBase) {
    private val speechTherapistDao = dataBase.SpeechTherapistDao()

    fun getSpeechTherapist(): Flow<List<SpeechTherapist>> {
       return speechTherapistDao.getSpeechTherapist()
    }

    fun setSpeechTherapist(id: Int) {
        speechTherapistDao.setSpeechTherapist(id)
    }

    fun deleteSpeechTherapist(id: Int) {
        speechTherapistDao.deleteSpeechTherapist(id)
    }

    fun deleteAllSpeechTherapist() {
        speechTherapistDao.deleteAllSpeechTherapist()
    }

    fun insertSpeechTherapist(speechTherapist: SpeechTherapist) {
        speechTherapistDao.insertSpeechTherapist(speechTherapist)
    }
}