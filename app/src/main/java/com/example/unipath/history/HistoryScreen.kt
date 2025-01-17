package com.example.unipath.history

import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unipath.diagnose.DiagnoseViewmodel
import com.example.unipath.ui.theme.Bg
import com.example.unipath.ui.theme.UnipathTheme
import androidx.compose.material3.IconButton
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.unipath.R
import com.example.unipath.route.Graph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.click) }
    val viewModel: DiagnoseViewmodel = viewModel()
    val historyList = viewModel.getDiagnoseHistory(context)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Bg,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        mediaPlayer.start()
                        navHostController.navigate(Graph.HOME) }) {
                        Icon(
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp),
                            imageVector = Icons.Filled.ArrowBack, contentDescription = "back button"
                        )
                    }
                },
                title = {
                    Text(
                        text = "Riwayat Tes",
                        style = MaterialTheme.typography.headlineMedium.copy(color = Color.Black),
                    )
                })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg)
                .padding(contentPadding)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(historyList) { history ->
                    CardHistory(
                        userName = history.userName,
                        diagnoseResult = history.diagnoseResult,
                        date = history.date
                    )
                }
            }
        }
    }
}

@Composable
fun CardHistory(userName: String, diagnoseResult: String, date: String) {
    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Black,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = userName, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = diagnoseResult, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date, style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview
@Composable
fun HistoryScreenPreview() {
    UnipathTheme {

    }
}
