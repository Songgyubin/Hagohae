package com.gyub.hagohae.mission

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gyub.hagohae.R
import net.skyscanner.backpack.compose.icon.BpkIcon
import net.skyscanner.backpack.compose.icon.BpkIconSize
import net.skyscanner.backpack.compose.icon.findByName
import net.skyscanner.backpack.compose.text.BpkText
import net.skyscanner.backpack.compose.theme.BpkTheme

/**
 * 미션 상세 탑 바
 *
 * @author   Gyub
 * @created  2024/09/29
 */
@Composable
fun MissionDetailTopAppBar(
    modifier: Modifier = Modifier,
    isEdit: Boolean,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BpkTheme.colors.canvas)
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier,
                onClick = onClick,
            ) {
                val icon = BpkIcon.findByName("native-android--back")
                icon?.let {
                    BpkIcon(
                        icon = it,
                        contentDescription = "Back",
                        size = BpkIconSize.Large,
                        tint = BpkTheme.colors.textPrimary
                    )
                }
            }

            BpkText(
                text = if (isEdit) stringResource(R.string.mission_edit) else stringResource(R.string.mission_detail),
                maxLines = 1,
                style = BpkTheme.typography.heading4,
                color = BpkTheme.colors.textPrimary,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.semantics { heading() },
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MissionDetailTopAppBarPreview() {
    BpkTheme {
        MissionDetailTopAppBar(
            isEdit = false
        )
    }
}
