package litus.forecastbot

import litus.forecastbot.config.AppConfiguration
import litus.forecastbot.feign.ForecastFeign
import litus.forecastbot.service.ForecastService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import java.lang.IndexOutOfBoundsException

@Component
class ForecastBot(
        private val botConfiguration: AppConfiguration,
        private val forecastService: ForecastService
): TelegramLongPollingBot() {

    override fun getBotToken(): String = botConfiguration.botToken

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val chatId = update.message.chatId

            when {
                update.message.isCommand -> {
                    when {
                        update.message.text == "/start" ->
                            this.sendApiMethod(SendMessage()
                                    .setChatId(chatId)
                                    .enableMarkdownV2(true)
                                    .setText("""
                                    Для получения погоды отправьте *местоположение*
                                    или воспользуйтесь командой "/weather _широта_ _долгота_"
                                    """.trimIndent()))
                        update.message.text.contains("/weather") -> {
                            try {
                                val splitString = update.message.text.split(" ")
                                val latitude = splitString[1].toFloat()
                                val longitude = splitString[2].toFloat()
                                this.sendApiMethod(SendMessage()
                                        .setChatId(chatId)
                                        .setText(forecastService.getForecastByCoordinates(
                                                latitude = latitude,
                                                longitude = longitude
                                        ).beautify()))
                            } catch (e: IndexOutOfBoundsException) {
                                this.sendApiMethod(SendMessage()
                                        .setChatId(chatId)
                                        .enableMarkdownV2(true)
                                        .setText("""
                                            Вы указали неверное число аргументов для команды /weather
                                            Попробуйте добавить после нее только *два* аргумента:
                                            _широту_ и _долготу_
                                        """.trimIndent()))
                            }
                        }
                        else -> return
                    }
                }
                update.message.location != null -> {
                    this.sendApiMethod(SendMessage()
                            .setChatId(chatId)
                            .setText(forecastService.getForecastByCoordinates(
                                    latitude = update.message.location.latitude,
                                    longitude = update.message.location.longitude
                            ).beautify())
                    )
                }
                else -> return
            }
        }
    }

    override fun getBotUsername(): String = botConfiguration.botName

}