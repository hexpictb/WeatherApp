package com.wheatherapptesttask.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.wheatherapptesttask.R
import com.wheatherapptesttask.domain.model.WeatherData

@Composable
fun WeatherCard(weatherData: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        AsyncImage(
            modifier = Modifier
                .size(123.dp)
                .align(Alignment.CenterHorizontally),
            contentDescription = null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(weatherData.iconUrl)
                .crossfade(true)
                .build()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = weatherData.city,
                color = Color(0XFF2C2C2C),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(11.dp))
            Icon(
                painter = painterResource(R.drawable.ic_gps_arrow),
                tint = Color.Unspecified,
                contentDescription = "gps_arrow"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = weatherData.temperature,
                color = Color(0XFF2C2C2C),
                fontSize = 70.sp,
                fontWeight = FontWeight.Medium
            )
            Canvas(
                modifier = Modifier
                    .size(8.dp)
                    .padding(top = 10.dp, start = 10.dp)
                    .align(Alignment.TopEnd)
            ) {
                drawCircle(
                    color = Color(0xff2c2c2c),
                    radius = 8f,
                    style = Stroke(1.5f)
                )
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Card(
            colors = CardDefaults.cardColors().copy(
                containerColor = Color(0xfff2f2f2)
            ),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TwoLineView(
                    title = "Humidity",
                    value = "${weatherData.humidity}%"
                )
                TwoLineView(
                    title = "UV",
                    value = weatherData.uvIndex
                )
                TwoLineView(
                    title = "Feels Like",
                    value = "${weatherData.feelsLike}°"
                )
            }

        }
    }
}

@Composable
private fun TwoLineView(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color(0xFFC4C4C4),
            lineHeight = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            color = Color(0xFF9A9A9A),
            lineHeight = 22.5.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun WeatherCardPreview() {
    val weatherData = WeatherData(
        city = "Mumbai",
        temperature = "30",
        condition = "Cold",
        humidity = "30",
        uvIndex = "3",
        feelsLike = "30",
        iconUrl = "http://cdn.weatherapi.com/weather/64x64/night/113.png"
    )
    Box(modifier = Modifier.background(Color.White)) {
        WeatherCard(weatherData)
    }
}
