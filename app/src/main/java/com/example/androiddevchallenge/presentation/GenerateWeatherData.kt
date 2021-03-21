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

import com.example.androiddevchallenge.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

internal object GenerateWeatherData {
    suspend fun getCurrentWeather() = flow {
        repeat(Int.MAX_VALUE) {
            emit(WARMER)
            delay(5000)
            emit(COOLER)
            delay(5000)
            emit(SAME)
            delay(5000)
            emit(COLDER_2)
            delay(5000)
        }
    }

    fun getWeatherOfWeek() = listOf(
        WeatherForecast("Mon, Mar 22", 2, 5, R.drawable.ic_cloudy),
        WeatherForecast("Tue, Mar 23", 1, 3, R.drawable.ic_storm),
        WeatherForecast("Wed, Mar 24", -1, 1, R.drawable.ic_snowy),
        WeatherForecast("Thu, Mar 25", 3, 7, R.drawable.ic_rain),
        WeatherForecast("Fri, Mar 26", 5, 9, R.drawable.ic_cloudy),
        WeatherForecast("Sat, Mar 27", 6, 11, R.drawable.ic_sun),
        WeatherForecast("Sun, Mar 28", 4, 7, R.drawable.ic_rain),
    )

    private val WARMER = CurrentWeather(
        Location("Berlin", "Germany"),
        2,
        12
    )

    private val COOLER = CurrentWeather(
        Location("Berlin", "Germany"),
        -6,
        6
    )

    private val SAME = CurrentWeather(
        Location("Berlin", "Germany"),
        0,
        6
    )

    private val COLDER_2 = CurrentWeather(
        Location("Berlin", "Germany"),
        -9,
        -3
    )
}
