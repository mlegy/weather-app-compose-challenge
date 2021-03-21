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
package com.example.androiddevchallenge

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.example.androiddevchallenge.presentation.*
import com.example.androiddevchallenge.presentation.WeatherViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            MyTheme {
                MyApp(viewModel)
            }
        }
    }
}

@Composable
internal fun MyApp(viewModel: WeatherViewModel) {
    val currentWeatherState by viewModel.currentWeather.observeAsState()
    currentWeatherState?.let { currentWeather ->
        Surface(
            color = currentWeather.weather.toBackgroundColor()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics(mergeDescendants = true) {
                        contentDescription = currentWeather.contentDescription
                    }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = currentWeather.location,
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(top = 48.dp, start = 32.dp, end = 32.dp)
                    )
                    Text(
                        text = "${currentWeather.weather.String()}C",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp)
                    )
                    val animationSpec = LottieAnimationSpec.RawRes(currentWeather.weather.toIcon())
                    val animationState = rememberLottieAnimationState(
                        autoPlay = true,
                        repeatCount = Integer.MAX_VALUE
                    )
                    LottieAnimation(
                        spec = animationSpec,
                        modifier = Modifier.requiredSize(300.dp),
                        animationState = animationState
                    )
                    ChangeInDegrees(value = currentWeather.changeInDegrees)
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentWeather.weather.toHint(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h3,
                    )
                }
            }
        }
        WeekView(viewModel = viewModel)
    }
}

@Composable
internal fun WeekView(viewModel: WeatherViewModel) {
    val forecast by viewModel.weatherOfWeek.observeAsState()

    forecast?.let { forecastData ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 48.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(8.dp)
                    .semantics(mergeDescendants = true) {
                        contentDescription = forecastData.contentDescription
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                items(forecastData) { data ->
                    Card(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .size(150.dp, 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = shapes.medium
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = data.dayName,
                                color = Color.DarkGray,
                                style = MaterialTheme.typography.h3,
                            )
                            Text(
                                text = "${data.high}/${data.low.String()}C",
                                color = Color.DarkGray,
                                style = MaterialTheme.typography.h3,
                            )
                            Image(
                                painter = painterResource(data.icon),
                                contentDescription = data.weather,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChangeInDegrees(@CelsiusDegree value: Int) {
    val icon = value.changeInDegreesToIcon()
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (icon != null) {
            Image(
                modifier = Modifier.requiredSize(48.dp),
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = value.changeInDegreesToString(),
            color = Color.White,
            style = MaterialTheme.typography.body1,
        )
    }
}
