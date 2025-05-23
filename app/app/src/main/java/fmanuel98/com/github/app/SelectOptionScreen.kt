package fmanuel98.com.github.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fmanuel98.com.github.R
import fmanuel98.com.github.app.components.FormattedPriceLabel

@Composable
fun SelectOptionScreen(
    subtotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        options.forEach { item ->
            Row(modifier = Modifier.selectable(
                selected = selectedValue == item,
                onClick = {
                    selectedValue = item
                    onSelectionChanged(item)
                }
            ),
                verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    })
                Text(item)
            }
        }
        Divider(thickness = 1.dp, modifier = modifier.padding(bottom = 16.dp))
        FormattedPriceLabel(
            subtotal = subtotal,
            modifier = Modifier
                .align(Alignment.End)
                .padding(
                    top = 16.dp, bottom = 16.dp
                )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun SelectOptionPreview() {
    SelectOptionScreen(
        subtotal = "299.99",
        options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    )
}