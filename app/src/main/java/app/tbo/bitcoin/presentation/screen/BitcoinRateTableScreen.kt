package app.tbo.bitcoin.presentation.screen

import androidx.compose.runtime.Composable
import app.tbo.bitcoin.domain.model.BitcoinValueElement
import app.tbo.bitcoin.presentation.composable.table.ExchangeRateTable
import app.tbo.bitcoin.presentation.composable.title.Title

/**
 * Composable function to display a screen with a title and a table of Bitcoin exchange rates.
 *
 * @param unit the unit for the Bitcoin exchange rates
 * @param mapped the list of BitcoinValueElement to display in the table
 */
@Composable
fun BitcoinRateTableScreen(unit: String?, mapped: List<BitcoinValueElement>) {
    Title(
        text = "BTC / ${unit} der letzten 14 Tage",
        fontSize = 26
    )
    ExchangeRateTable(mapped = mapped)
}