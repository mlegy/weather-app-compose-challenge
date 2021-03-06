/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class WeatherViewModel : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> = _currentWeather

    private val _weatherOfWeek = MutableLiveData<List<WeatherForecast>>()
    val weatherOfWeek: LiveData<List<WeatherForecast>> = _weatherOfWeek

    init {
        viewModelScope.launch {
            GenerateWeatherData.getCurrentWeather().collect { _currentWeather.postValue(it) }
        }
        val weatherOfWeek = GenerateWeatherData.getWeatherOfWeek()
        _weatherOfWeek.value = weatherOfWeek
    }
}
