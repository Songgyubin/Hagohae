package com.gyub.hagohae.mission

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gyub.hagohae.R
import net.skyscanner.backpack.compose.card.BpkCard
import net.skyscanner.backpack.compose.card.BpkCardCorner
import net.skyscanner.backpack.compose.theme.BpkTheme

/**
 * 미션 상세 화면
 *
 * @author   Gyub
 * @created  2024/09/24
 */
@Composable
fun MissionDetailRoute(
    innerPadding: PaddingValues,
    onBackClick: () -> Unit,
) {
    MissionDetailScreen(innerPadding)
}

@Composable
fun MissionDetailScreen(
    innerPadding: PaddingValues,

    ) {
    Box(
        modifier = Modifier
            .background(BpkTheme.colors.canvas)
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column {
            MissionDetailTopAppBar(
                modifier = Modifier.background(BpkTheme.colors.canvas),
                isEdit = false
            )
            Spacer(modifier = Modifier.height(20.dp))
            MissionDetailContent()
        }
    }
}

@Composable
fun MissionDetailContent(

) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = spacedBy(12.dp)
    ) {
        MissionTitle()
        MissionContent()
        MissionBlockingApp()
        BlockStartTime()
        BlockEndTime()
    }
}

@Composable
fun MissionBlockingApp() {
    MissionCard {
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = "차단할 앱 선택하기"
            )
        }
    }
}

@Composable
fun MissionTitle(
    title: String = "",
) {
    MissionCard {
        MissionDetailCardSectionTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {},
            placeholder = "미션 제목",
        )
    }
}

@Composable
fun MissionDetailCardSectionTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BpkTheme.colors.surfaceDefault,
            unfocusedContainerColor = BpkTheme.colors.surfaceDefault,
            disabledContainerColor = BpkTheme.colors.surfaceDefault,
            focusedIndicatorColor = BpkTheme.colors.surfaceDefault,
            unfocusedIndicatorColor = BpkTheme.colors.surfaceDefault,
            disabledIndicatorColor = BpkTheme.colors.surfaceDefault,
        ),
        placeholder = {
            Text(
                placeholder,
                color = BpkTheme.colors.textPrimary
            )
        },
    )
}

@Composable
fun MissionContent(
    title: String = "",
) {
    MissionCard {
        Column {
            MissionDetailCardSectionTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = {},
                placeholder = "미션 내용",
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun BlockStartTime() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("blocked_apps_prefs", Context.MODE_PRIVATE)
    val (startHour, setStartHour) = remember { mutableStateOf(sharedPreferences.getInt("block_start_hour", 0)) }
    val (startMinute, setStartMinute) = remember { mutableStateOf(sharedPreferences.getInt("block_start_minute", 0)) }

    MissionCard {
        Column {
            Text(text = "앱 차단 시작 시간", style = BpkTheme.typography.heading5)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    showTimePicker(
                        context = context,
                        initialHour = startHour,
                        initialMinute = startMinute
                    ) { hour, minute ->
                        setStartHour(hour)
                        setStartMinute(minute)
                        sharedPreferences.edit()
                            .putInt("block_start_hour", hour)
                            .putInt("block_start_minute", minute)
                            .apply()
                    }
                }
            ) {
                Text(text = "시작 시간 선택")
            }
            Text(text = String.format("선택된 시간: %02d:%02d", startHour, startMinute))
        }
    }
}

@Composable
fun BlockEndTime() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("blocked_apps_prefs", Context.MODE_PRIVATE)
    val (endHour, setEndHour) = remember { mutableStateOf(sharedPreferences.getInt("block_end_hour", 0)) }
    val (endMinute, setEndMinute) = remember { mutableStateOf(sharedPreferences.getInt("block_end_minute", 0)) }

    MissionCard {
        Column {
            Text(text = "앱 차단 종료 시간", style = BpkTheme.typography.heading5)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    showTimePicker(
                        context = context,
                        initialHour = endHour,
                        initialMinute = endMinute
                    ) { hour, minute ->
                        setEndHour(hour)
                        setEndMinute(minute)
                        sharedPreferences.edit()
                            .putInt("block_end_hour", hour)
                            .putInt("block_end_minute", minute)
                            .apply()
                    }
                }
            ) {
                Text(text = "종료 시간 선택")
            }
            Text(text = String.format("선택된 시간: %02d:%02d", endHour, endMinute))
        }
    }
}

fun showTimePicker(
    context: Context,
    initialHour: Int = 0,
    initialMinute: Int = 0,
    onTimeSelected: (Int, Int) -> Unit,
) {
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int -> onTimeSelected(hour, minute) },
        initialHour,
        initialMinute,
        true
    )
    timePickerDialog.show()
}

@Composable
fun MissionCard(
    content: @Composable ColumnScope.() -> Unit,
) {
    BpkCard(
        modifier = Modifier.fillMaxWidth(),
        corner = BpkCardCorner.Large,
        content = content
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MissionDetailScreenPreview() {
    BpkTheme {
        MissionDetailScreen(
            innerPadding = PaddingValues()
        )
    }
}
