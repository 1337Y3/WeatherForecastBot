package litus.forecastbot.model.dto.weather

import com.fasterxml.jackson.annotation.JsonProperty

data class Rain(
        @JsonProperty("1h")
        val hours: Float
)