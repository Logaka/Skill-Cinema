package sdu.project.cinemaapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ShowContent(
    modifier: Modifier = Modifier,
    tabs: List<String>, // Список вкладок
    title: String
) {
    // Состояние для хранения текущей выбранной вкладки
    val selectedTab = remember { mutableStateOf(tabs.first()) }

    Column(
        modifier = modifier
            .padding(horizontal = 26.dp, vertical = 16.dp)
    ) {
        Text(text = title, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .border(width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (selectedTab.value == tab) Color.Blue else Color.White)
                        .clickable { selectedTab.value = tab }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab,
                        color = if (selectedTab.value == tab) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

//        Spacer(modifier = Modifier.height(16.dp))

    }
}