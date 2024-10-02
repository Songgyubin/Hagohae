package com.gyub.hagohae.mission

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gyub.hagohae.R
import net.skyscanner.backpack.compose.navigationbar.BpkTopNavBar
import net.skyscanner.backpack.compose.navigationbar.NavIcon
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
) {
    BpkTopNavBar(
        navIcon = NavIcon.Back(
            contentDescription = "Back",
            onClick = {}
        ),
        title = if (isEdit) stringResource(R.string.mission_edit) else stringResource(R.string.mission_detail),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun MissionDetailTopAppBarPreview() {
    BpkTheme {
        MissionDetailTopAppBar(
            isEdit = false
        )
    }
}