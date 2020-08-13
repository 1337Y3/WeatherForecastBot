package litus.forecastbot.service

import litus.forecastbot.config.AppConfiguration
import litus.forecastbot.feign.ForecastFeign
import litus.forecastbot.model.dto.Forecast
import org.springframework.stereotype.Service

@Service
class ForecastService(
        private val config: AppConfiguration,
        private val forecastClient: ForecastFeign
) {

    fun getForecastByCoordinates(latitude: Float, longitude: Float): Forecast {
        val params = mapOf(
                "lat" to latitude,
                "lon" to longitude,
                "exclude" to "minutely,hourly,daily",
                "units" to "metric",
                "lang" to "ru",
                "appid" to config.forecastApiKey)
        return forecastClient.getForecast(params)
    }
}