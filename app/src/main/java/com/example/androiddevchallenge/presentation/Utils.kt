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

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.R

fun @receiver:CelsiusDegree Int.toBackgroundColor() = when {
    this < 0 -> Color(0xFF8F8F8F)
    this < 10 -> Color(0xFF2196F3)
    else -> Color(0xFFFF5722)
}

fun @receiver:CelsiusDegree Int.toIcon() = when {
    this < 0 -> R.raw.weather_cloudy
    this < 10 -> R.raw.weather_clear
    else -> R.raw.weather_sunny
}

fun @receiver:CelsiusDegree Int.toHint() = when {
    this < 0 -> "It's cold out side .. better keep home"
    this < 10 -> "Weather is clear today! let's go for a walk"
    else -> "It's sunny outside"
}

fun @receiver:CelsiusDegree Int.String() = "$this°"
