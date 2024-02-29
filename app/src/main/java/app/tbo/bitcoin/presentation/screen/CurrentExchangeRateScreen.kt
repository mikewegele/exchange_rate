package app.tbo.bitcoin.presentation.screen

import androidx.compose.runtime.Composable
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import app.tbo.bitcoin.presentation.composable.current.CurrentExchangeRate
import app.tbo.bitcoin.presentation.composable.title.Title

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