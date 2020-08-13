package litus.forecastbot.feign

import litus.forecastbot.model.dto.Forecast
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "OneWeather", url = "https://api.openweathermap.org/data/2.5")
interface ForecastFeign {

    @GetMapping("onecall")
    fun getForecast(@SpringQueryMap params: Map<String, Any>): Forecast
}