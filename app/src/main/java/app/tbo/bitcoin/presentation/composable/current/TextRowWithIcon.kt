package app.tbo.bitcoin.presentation.composable.current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import kotlinx.coroutines.delay

@Composable
fun TextRowWithIcon(rate: ExchangeRateElement) {
    var second by remember { mutableIntStateOf(0) }

    LaunchedEffect(rate.value) {
        second = 0
        while (true) {
            delay(1000)
            second++
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "1",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                text = "${String.format("%.2f", rate.value)}${rate.unit}",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Text(
            fontSize = 12.sp,
            text = "Zuletzt aktualisiert vor ${second} Sekunden",
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            textAlign = TextAlign.End
        )
    }
}