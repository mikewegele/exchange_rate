package app.tbo.bitcoin.presentation.composable.current

import androidx.compose.runtime.Composable
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import app.tbo.bitcoin.util.map.getKeyByPropertyValue

@Composable
fun CurrentExchangeRate(
    exchangeRate: ExchangeRate,
    selectedCurrency: ExchangeRateElement,
    onSelectedCurrencyChanged: (ExchangeRateElement) -> Unit,
    onVsCurrencyChanged: (String) -> Unit) {

    val currentRate = exchangeRate.rates.values.toList().find { it.name == selectedCurrency.name }

    fun selectedCurrencyHandler(current: ExchangeRateElement) {
        getKeyByPropertyValue(exchangeRate.rates, "name", current.name)?.let {
            onVsCurrencyChanged(it)
        }
    }

    HeaderDropdownMenu(
        exchangeRate = exchangeRate,
        selectedCurrency = selectedCurrency,
        onSelectedCurrencyChanged = { element ->
            if (element != null) {
                onSelectedCurrencyChanged(element)
                selectedCurrencyHandler(element)
            }
        })

    currentRate?.let { TextRowWithIcon(rate = it) }
}