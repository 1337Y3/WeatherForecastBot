package litus.forecastbot.model.dto

import litus.forecastbot.model.dto.weather.CurrentForecast
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Forecast(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Long,
    val current: CurrentForecast
) {
    fun beautify(): String =
            "По вашим координатам ($lat, $lon) температура ${current.temp}ºC, " +
            "ощущается как ${current.feels_like}ºC, " +
            "влажность ${current.humidity}%, " +
            "давление ${current.pressure} мм. рт. с., " +
            current.weather.map { it.description }.joinToString(", ")

    private fun getCurrentTime(): String =
            Instant.ofEpochMilli(current.dt).atZone(ZoneId.of(timezone))
                    .format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"))
}