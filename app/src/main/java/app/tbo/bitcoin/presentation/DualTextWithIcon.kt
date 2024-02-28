package app.tbo.bitcoin.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.data.local.ExchangeRateUnit

@Composable
fun DualTextWithIcon(euroRate: ExchangeRateUnit) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "1",
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            text = "${String.format("%.2f", euroRate.value)} ${euroRate.unit}",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}