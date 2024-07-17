package com.example.unipath.diagnose

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unipath.route.Graph
import com.example.unipath.ui.theme.Bg
import com.example.unipath.ui.theme.CatcareexpertsystemTheme
import com.example.unipath.ui.theme.Primary
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
        .padding(20.dp)
    ) {
        currentQuestion?.let {
            QuestionCard(
                question = it,
                onYesClick = { viewmodel.answerQuestion(it.gejalaCode, 1.0) },
                onNoClick = { viewmodel.answerQuestion(it.gejalaCode, cf = 0.0) }
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
    val sharedPreferences = context.getSharedPreferences("PetPreferences", Context.MODE_PRIVATE)
    val petName = sharedPreferences.getString("temp_pet_name", "Unknown") ?: "Unknown"

    val highestResult = results.maxByOrNull { it.cf }

    Column(
        modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        highestResult?.let {
            if (it.cf > 0){
                Text(
                    textAlign = TextAlign.Center,
                    text = "Jurusan yang cocok adalah ${it.penyakit} (${"%.2f".format(it.cf)}%)",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier,
                    color = Color.Black
                )
            }else{
                Text(
                    textAlign = TextAlign.Center,
                    text = "Tidak ada jurusan yang cocok",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier,
                    color = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier,
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
                    .padding(20.dp)
                    .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(10.dp))
            ) {
                results.forEach { result ->
                    Text(
                        text = "${result.penyakit} : ${"%.2f".format(result.cf)}%",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)
                            .padding(top = 6.dp, bottom = 6.dp),
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.saveDiagnoseResult(context, petName)
                navController.navigate(Graph.SCREEN_HISTORY)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Tombol),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Simpan", color = Color.White)
        }
    }
}

@Composable
fun QuestionCard(question: Gejala, onYesClick: () -> Unit, onNoClick: () -> Unit) {

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
                Text(text = question.gejalaName, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onNoClick,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Tombol)
                    ) {
                        Text(text = "Tidak", color = Color.White)
                    }
                    Button(
                        onClick = onYesClick,
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
    CatcareexpertsystemTheme {
        QuestionScreen(rememberNavController())
    }
}