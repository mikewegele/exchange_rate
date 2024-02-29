package app.tbo.bitcoin.presentation.composable.current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Composable function to display a row with two text values.
 *
 * @param firstValue the first text value to display
 * @param secondValue the second text value to display
 */
@Composable
fun TextRow(firstValue: String, secondValue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = firstValue,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = secondValue,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}