package org.akshev.health_score

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import healthscore.composeapp.generated.resources.Res
import healthscore.composeapp.generated.resources.age
import healthscore.composeapp.generated.resources.calculate
import healthscore.composeapp.generated.resources.cholesterol_level
import healthscore.composeapp.generated.resources.first_aid_status
import healthscore.composeapp.generated.resources.full_name
import healthscore.composeapp.generated.resources.glucose_level
import healthscore.composeapp.generated.resources.gto
import healthscore.composeapp.generated.resources.medical_exam
import healthscore.composeapp.generated.resources.observation
import healthscore.composeapp.generated.resources.vaccination
import healthscore.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            Modifier.fillMaxSize()
                .scrollable(
                    state = rememberScrollState(0),
                    orientation = Orientation.Vertical,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputField(
                    label = { Text(stringResource(Res.string.full_name)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                InputField(
                    label = { Text(stringResource(Res.string.glucose_level)) }
                )
                InputField(
                    label = { Text(stringResource(Res.string.cholesterol_level)) }
                )
                InputField(
                    label = { Text(stringResource(Res.string.age)) }
                )
                val focusManager = LocalFocusManager.current
                InputField(
                    label = { Text(stringResource(Res.string.weight)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            defaultKeyboardAction(ImeAction.Done)
                        }
                    )
                )

                CheckBoxLine { stringResource(Res.string.medical_exam) }
                CheckBoxLine { stringResource(Res.string.observation) }
                CheckBoxLine { stringResource(Res.string.gto) }
                CheckBoxLine { stringResource(Res.string.vaccination) }
                CheckBoxLine { stringResource(Res.string.first_aid_status) }
            }

            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier.size(100.dp),
                    imageVector = Icons.Rounded.ThumbUp,
                    contentDescription = null
                )
            }

            Button(
                modifier = Modifier.padding(bottom = 20.dp)
                    .height(50.dp),
                onClick = { showContent = !showContent }) {
                Text(stringResource(Res.string.calculate))
            }
        }
    }
}

@Composable
fun CheckBoxLine(
    modifier: Modifier = Modifier,
    text: @Composable (() -> String)
) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(0.95f)
            .clickable { isChecked = !isChecked },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text())
        Checkbox(isChecked, onCheckedChange = { isChecked = !isChecked })
    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Number
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var inputText by remember { mutableStateOf("") }
    OutlinedTextField(
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier.fillMaxWidth(0.95f),
        value = inputText,
        onValueChange = { inputText = it },
        label = label,
        trailingIcon = { Icons.Default.Clear }
    )
}
