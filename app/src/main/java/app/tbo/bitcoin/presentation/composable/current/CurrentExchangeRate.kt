package app.tbo.bitcoin.presentation.composable.current

import androidx.compose.runtime.Composable
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import app.tbo.bitcoin.util.map.getKeyByPropertyValue

/**
 * Composable function to display the current exchange rate with a dropdown menu for selecting a currency.
 *
 * @param exchangeRate the exchange rate data
 * @param selectedCurrency the currently selected currency
 * @param onSelectedCurrencyChanged the callback for when the selected currency changes
 * @param onVsCurrencyChanged the callback for when the vs currency changes
 */
@Composable
fun CurrentExchangeRate(
    exchangeRate: ExchangeRate,
    selectedCurrency: ExchangeRateElement,
    onSelectedCurrencyChanged: (ExchangeRateElement) -> Unit,
    onVsCurrencyChanged: (String) -> Unit
) {

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