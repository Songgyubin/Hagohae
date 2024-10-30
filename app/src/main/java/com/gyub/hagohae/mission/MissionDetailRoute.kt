package com.gyub.hagohae.mission

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        verticalArrangement = spacedBy(15.dp)
    ) {
        MissionTitle()
        MissionContent()
        BlockStartTime()
        BlockEndTime()
        BlockedApps()
    }
}

@Composable
fun MissionTitle(
    title: String = "",
) {
    MissionCard {
        Column {
            MissionDetailCardSectionTextField(
                modifier = Modifier.fillMaxSize(),
                value = title,
                onValueChange = {},
                placeholder = "미션 제목",
            )
        }
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
fun MissionContent() {
    MissionCard {

    }
}

@Composable
fun BlockStartTime() {
    MissionCard {

    }
}

@Composable
fun BlockEndTime() {
    MissionCard {

    }
}

@Composable
fun BlockedApps() {
    MissionCard {

    }
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
