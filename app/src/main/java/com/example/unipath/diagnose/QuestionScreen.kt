package com.example.unipath.diagnose

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unipath.R
import com.example.unipath.route.Graph
import com.example.unipath.ui.theme.Bg
import com.example.unipath.ui.theme.UnipathTheme
import com.example.unipath.ui.theme.Tombol


@Composable
fun QuestionScreen(navController: NavHostController) {
    val viewmodel: DiagnoseViewmodel = viewModel()
    val question = viewmodel.quetion.collectAsState().value
    val currentQuestionIndex = viewmodel.currentQuestionIndex.collectAsState().value
    val currentQuestion = question.getOrNull(currentQuestionIndex)
    val isFinish = viewmodel.isFinish.collectAsState().value
    val results = viewmodel.results.collectAsState().value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Bg)
        .padding(10.dp)
    ) {
        currentQuestion?.let {
            QuestionCard(
                question = it,
                onYesClick = { viewmodel.answerQuestion(it.minatCode, 1.0) },
                onNoClick = { viewmodel.answerQuestion(it.minatCode, cf = 0.0) }
            )
        }
        if (currentQuestionIndex > question.size - 1 && isFinish){
            ResultScreen(results = results, viewModel = viewmodel, navController = navController)
        }

    }
}

@Composable
fun ResultScreen(results: List<Result>, viewModel: DiagnoseViewmodel, navController: NavHostController) {
    val context = LocalContext.current
    val mediaPlayer1 = remember { MediaPlayer.create(context, R.raw.button) }
    val mediaPlayer2 = remember { MediaPlayer.create(context, R.raw.click) }
    val sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    val userName = sharedPreferences.getString("temp_user_name", "Unknown") ?: "Unknown"

    val highestResult = results.maxByOrNull { it.cf }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        highestResult?.let {
            if (it.cf > 0){
                Text(

                    text = "Jurusan yang cocok adalah",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(top = 70.dp, bottom = 20.dp),
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "image",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize()
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "${it.jurusan}",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 20.dp),
                    color = Color.Black
                )
            }else{
                Text(
                    textAlign = TextAlign.Center,
                    text = "Tidak ada jurusan yang cocok",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(top = 70.dp, bottom = 30.dp),
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "image",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize()
                        .padding(bottom = 10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                mediaPlayer1.start()
                navController.navigate(Graph.SCREEN_QUESTION) },
            colors = ButtonDefaults.buttonColors(Bg),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Tes Ulang", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                mediaPlayer2.start()
                viewModel.saveDiagnoseResult(context, userName = userName)
                navController.navigate(Graph.SCREEN_HISTORY)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Tombol),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Simpan Hasil", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Black, thickness = 3.dp)
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hasil Tes",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier,
                color = Color.Black)
            Card(
                colors = CardColors(
                    containerColor = Color.Black,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.Black,
                ),
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(10.dp))
            ) {
                results.forEach { result ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black,
                            disabledContentColor = Color.Black,
                            disabledContainerColor = Color.Black,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "${result.jurusan} : ${"%.2f".format(result.cf)}%",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp, end = 10.dp)
                                    .padding(top = 5.dp, bottom = 5.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            }
    }
}

@Composable
fun QuestionCard(question: Minat, onYesClick: () -> Unit, onNoClick: () -> Unit) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.score) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.Black,
            ),
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
            ) {
                Text(text = question.minatName, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            mediaPlayer.start()
                            onNoClick()
                                  },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Tombol)
                    ) {
                        Text(text = "Tidak", color = Color.White)
                    }
                    Button(
                        onClick = {
                            mediaPlayer.start()
                            onYesClick()
                                  },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Tombol)
                    ) {
                        Text(text = "Ya", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuestionView() {
    UnipathTheme {
        QuestionScreen(rememberNavController())
    }
}