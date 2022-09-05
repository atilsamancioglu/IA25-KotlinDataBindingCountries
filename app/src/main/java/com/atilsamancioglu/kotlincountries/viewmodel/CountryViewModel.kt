package com.atilsamancioglu.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.kotlincountries.model.Country
import com.atilsamancioglu.kotlincountries.service.CountryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            withContext(Dispatchers.Main) {
                countryLiveData.value = country
            }
        }
    }
}