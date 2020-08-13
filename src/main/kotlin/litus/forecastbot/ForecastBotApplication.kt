package litus.forecastbot

import litus.forecastbot.config.AppConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = [AppConfiguration::class])
class ForecastBotApplication

fun main(args: Array<String>) {
	ApiContextInitializer.init()
	runApplication<ForecastBotApplication>(*args)
}
