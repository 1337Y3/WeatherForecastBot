package litus.forecastbot.model.dto.weather

data class CurrentForecast(
        val dt: Long,
        val sunrise: Long,
        val sunset: Long,
        val temp: Float,
        val feels_like: Float,
        val pressure: Int,
        val humidity: Int,
        val dew_point: Float,
        val uvi: Float,
        val clouds: Int,
        val visibility: Int,
        val wind_speed: Float,
        val wind_deg: Int,
        val weather: List<Weather>,
        val rain: Rain?,
        val snow: Snow?
)