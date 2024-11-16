package com.chalco.jose.laboratoriocalifcado03

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01ViewModel {
    val teacherList = MutableLiveData<List<TeacherResponse>>()

    val isLoading = MutableLiveData<Boolean>()

    val errorApi = MutableLiveData<String>()

    init {
        getAllTeachers()
    }

    private fun getAllTeachers() {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(TeacherApi::class.java).getTeachers()
                if (call.isSuccessful) {
                    call.body()?.let {
                        isLoading.postValue(false)
                        teacherList.postValue(it.teachers)
                    }
                }
            } catch (e: Exception) {
                errorApi.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}