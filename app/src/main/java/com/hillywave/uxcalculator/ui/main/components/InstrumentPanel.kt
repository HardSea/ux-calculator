package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.R
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType

@Composable
fun InstrumentPanel(
    modifier: Modifier = Modifier,
    onInstrumentClick: (InstrumentType) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier.padding(end = 8.dp),
            iconRes = R.drawable.ic_history,
            onClick = {
                onInstrumentClick(InstrumentType.HISTORY)
            }
        )
        IconButton(
            iconRes = R.drawable.ic_settings,
            onClick = {
                onInstrumentClick(InstrumentType.SETTINGS)
            }
        )
        Divider(modifier = Modifier.weight(1f), thickness = 0.dp, color = Color.Transparent)
        IconButton(
            iconRes = R.drawable.ic_backspace,
            onClick = {
                onInstrumentClick(InstrumentType.BACKSPACE)
            }
        )
    }
}
