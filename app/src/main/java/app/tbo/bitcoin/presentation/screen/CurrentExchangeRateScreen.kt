package app.tbo.bitcoin.presentation.screen

import androidx.compose.runtime.Composable
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import app.tbo.bitcoin.presentation.composable.current.CurrentExchangeRate
import app.tbo.bitcoin.presentation.composable.title.Title

/**
 * Composable function to display a screen with a title and the current exchange rate section.
 *
 * @param exchangeRate the exchange rate data
 * @param selectedCurrency the currently selected currency
 * @param onSelectedCurrencyChanged the callback for when the selected currency changes
 * @param onVsCurrencyChanged the callback for when the vs currency changes
 */
@Composable
fun CurrentExchangeRateScreen(
    exchangeRate: ExchangeRate,
    selectedCurrency: ExchangeRateElement,
    onSelectedCurrencyChanged: (ExchangeRateElement) -> Unit,
    onVsCurrencyChanged: (String) -> Unit
) {
    Title(
        text = "Wechselkurs",
        fontSize = 30
    )
    CurrentExchangeRate(
        exchangeRate = exchangeRate,
        selectedCurrency = selectedCurrency,
        onSelectedCurrencyChanged = onSelectedCurrencyChanged,
        onVsCurrencyChanged = onVsCurrencyChanged
    )
}