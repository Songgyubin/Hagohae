package com.gyub.hagohae.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.gyub.hagohae.R
import net.skyscanner.backpack.compose.card.BpkCard
import net.skyscanner.backpack.compose.card.BpkCardElevation
import net.skyscanner.backpack.compose.card.BpkCardPadding
import net.skyscanner.backpack.compose.text.BpkText
import net.skyscanner.backpack.compose.theme.BpkTheme

/**
 * 홈화면
 *
 * @author   Gyub
 * @created  2024/09/21
 */
@Composable
fun HomeRoute(
    innerPadding: PaddingValues = PaddingValues(),
    navigateMissionDetail: (Boolean) -> Unit,
) {
    HomeScreen(
        innerPadding = innerPadding,
        navigateMissionDetail = navigateMissionDetail
    )
}

@Composable
fun HomeScreen(
    innerPadding: PaddingValues = PaddingValues(),
    navigateMissionDetail: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(BpkTheme.colors.canvas)
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column {
            BpkText(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                text = stringResource(R.string.app_slogan),
                color = BpkTheme.colors.textPrimary,
                style = BpkTheme.typography.heading2
            )

            Missions(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp),
                navigateMissionDetail = navigateMissionDetail
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            shape = CircleShape,
            containerColor = BpkTheme.colors.corePrimary,
            onClick = { navigateMissionDetail(true) }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

@Composable
fun Missions(
    modifier: Modifier = Modifier,
    navigateMissionDetail: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        BpkText(
            text = stringResource(R.string.todo_list),
            style = BpkTheme.typography.heading4,
            color = BpkTheme.colors.textPrimary,
            modifier = Modifier.padding(start = 20.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp),
            verticalArrangement = spacedBy(30.dp),
        ) {
            items(10) {
                MissionItem(
                    missionTitle = "자기 전에 오늘 회고록 작성하기",
                    blockingStartTime = "오후 11시 30분",
                    navigateMissionDetail = navigateMissionDetail
                )
            }
        }
    }
}

@Composable
fun MissionItem(
    missionTitle: String,
    blockingStartTime: String,
    navigateMissionDetail: (Boolean) -> Unit,
) {
    BpkCard(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.0f),
        padding = BpkCardPadding.None,
        onClick = { navigateMissionDetail(true) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(BpkTheme.colors.surfaceDefault),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .weight(1.5f),
                verticalArrangement = spacedBy(20.dp)
            ) {
                BpkText(
                    modifier = Modifier.padding(top = 20.dp),
                    text = missionTitle,
                    maxLines = 2,
                    color = BpkTheme.colors.textPrimary,
                    overflow = TextOverflow.Ellipsis,
                    style = BpkTheme.typography.label1
                )
                TimeLabel(blockingStartTime = blockingStartTime)
            }

            AppIconsContainer(
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 10.dp)
                    .align(Alignment.CenterVertically),
                icons = listOf(
                    R.drawable.tmp_kakao,
                    R.drawable.tmp_insta,
                    R.drawable.tmp_youtube,
                    R.drawable.tmp_coupangeats
                )
            )
        }
    }
}

@Composable
private fun TimeLabel(blockingStartTime: String) {
    Column {
        BpkText(
            text = stringResource(R.string.start_blocking_time),
            style = BpkTheme.typography.label2,
            color = BpkTheme.colors.textPrimary,
        )

        Spacer(modifier = Modifier.height(2.dp))

        BpkText(
            text = blockingStartTime,
            color = BpkTheme.colors.textPrimary,
            style = BpkTheme.typography.label3.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun AppIconsContainer(
    modifier: Modifier = Modifier,
    icons: List<Int>,
) {
    var boxSize by remember { mutableStateOf(IntSize.Zero) }

    BpkCard(
        modifier = modifier
            .onSizeChanged { newSize ->
                boxSize = newSize
            },
        padding = BpkCardPadding.None,
        elevation = BpkCardElevation.None
    ) {
        FourBoxIcons(
            icons = icons,
            boxSize = boxSize
        )
    }
}

@Composable
fun FourBoxIcons(
    modifier: Modifier = Modifier,
    icons: List<Int>,
    boxSize: IntSize,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BpkTheme.colors.canvasContrast)
            .padding(2.dp),
    ) {
        icons.forEachIndexed { index, icon ->
            BpkCard(
                modifier = Modifier
                    .align(getAlignForIconIndex(index))
                    .size(width = (boxSize.width / 6).dp, height = (boxSize.height / 6).dp)
                    .padding(getPaddingForIconIndex(index)),
                padding = BpkCardPadding.None,
                elevation = BpkCardElevation.None,
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

private fun getAlignForIconIndex(index: Int): Alignment {
    return when (index) {
        0 -> Alignment.TopStart
        1 -> Alignment.TopEnd
        2 -> Alignment.BottomStart
        else -> Alignment.BottomEnd
    }
}

private fun getPaddingForIconIndex(index: Int): PaddingValues {
    return when (index) {
        0 -> PaddingValues(end = 1.dp, bottom = 1.dp)
        1 -> PaddingValues(start = 1.dp, bottom = 1.dp)
        2 -> PaddingValues(end = 1.dp, top = 1.dp)
        else -> PaddingValues(start = 1.dp, top = 1.dp)
    }
}

@Preview
@Composable
private fun AppIconsContainerPreview() {
    BpkTheme {
        val icons = listOf(R.drawable.tmp_kakao, R.drawable.tmp_insta, R.drawable.tmp_youtube, R.drawable.tmp_coupangeats)
        AppIconsContainer(
            modifier = Modifier.size(100.dp),
            icons = icons
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    BpkTheme {
        HomeScreen(navigateMissionDetail = {})
    }
}

@Preview
@Composable
private fun MissionItemPreview() {
    BpkTheme {
        MissionItem(
            missionTitle = "자기 전에 오늘 회고록 작성하기",
            blockingStartTime = "오후 11시 30분",
            navigateMissionDetail = { }
        )
    }
}
