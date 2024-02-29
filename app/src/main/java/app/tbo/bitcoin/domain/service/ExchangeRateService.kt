package app.tbo.bitcoin.domain.service

import android.os.Build
import androidx.annotation.RequiresApi
import app.tbo.bitcoin.domain.model.BitcoinValue
import app.tbo.bitcoin.domain.model.BitcoinValueElement
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExchangeRateService {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBTCtoCurrency(
        selected: ExchangeRateElement,
        bitcoinValue: BitcoinValue
    ): List<BitcoinValueElement> {
        val currentDate: LocalDate = LocalDate.now().minusDays(bitcoinValue.prices.size.toLong())
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return bitcoinValue.prices.flatMapIndexed { day, pricesOfDay ->
            val currentDateMinusDays = currentDate.plusDays(day.toLong())
            val formattedDate = currentDateMinusDays.format(formatter)
            pricesOfDay.drop(1)
                .map { price -> BitcoinValueElement(formattedDate, price, selected.unit) }
        }.reversed()
    }

    fun getExchangeRateByName(exchanges: ExchangeRate, name: String): ExchangeRateElement? {
        return exchanges.rates.values.toList().find { it.name == name }
    }

}