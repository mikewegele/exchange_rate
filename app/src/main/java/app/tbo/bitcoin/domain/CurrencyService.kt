package app.tbo.bitcoin.domain

import android.os.Build
import androidx.annotation.RequiresApi
import app.tbo.bitcoin.api.client.CurrencyApi
import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.local.CurrencyObject
import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.data.local.ExchangeRateUnit
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CurrencyService {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBTCtoCurrency(selected: ExchangeRateUnit, currency: Currency): List<CurrencyObject> {
        val currentDate: LocalDate = LocalDate.now().minusDays(currency.prices.size.toLong())
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return currency.prices.flatMapIndexed { day, pricesOfDay ->
            val currentDateMinusDays = currentDate.plusDays(day.toLong())
            val formattedDate = currentDateMinusDays.format(formatter)
            pricesOfDay.drop(1).map { price -> CurrencyObject(formattedDate, price, selected.unit) }
        }.reversed()
    }

    fun getExchangeRateByName(exchanges: ExchangeRate, name: String): ExchangeRateUnit? {
        return exchanges.rates.values.toList().find { it.name == name }
    }

}