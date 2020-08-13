package litus.forecastbot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Primary

@Primary
@ConfigurationProperties("app")
data class AppConfiguration(
    var botToken: String = "",
    var botName: String = "",
    var forecastApiKey: String = ""
)