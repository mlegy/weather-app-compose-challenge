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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

val poppins: FontFamily = FontFamily(
    Font(R.font.poppins_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(R.font.poppins_light, weight = FontWeight.Light, style = FontStyle.Normal),
    Font(R.font.poppins_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
)

val openSansCondensed: FontFamily = FontFamily(
    Font(R.font.open_sans_condensed_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
)
val typography = Typography(
    h1 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 96.sp,
        color = Color.White
    ),
    h2 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.White
    ),
    h3 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Light,
        fontSize = 19.sp,
        color = Color.White
    ),
    h4 = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = Color.White
    ),
    body1 = TextStyle(
        fontFamily = openSansCondensed,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
)
