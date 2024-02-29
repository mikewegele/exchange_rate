package app.tbo.bitcoin.presentation.screen

import androidx.compose.runtime.Composable
import app.tbo.bitcoin.domain.model.BitcoinValueElement
import app.tbo.bitcoin.presentation.composable.table.ExchangeRateTable
import app.tbo.bitcoin.presentation.composable.title.Title

@Composable
fun BitcoinRateTableScreen(unit: String?, mapped: List<BitcoinValueElement>) {
    Title(
        text = "BTC / ${unit} der letzten 14 Tage",
        fontSize = 26)
    ExchangeRateTable(mapped = mapped)
}