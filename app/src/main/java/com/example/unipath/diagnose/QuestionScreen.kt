package com.example.unipath.diagnose

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
        .padding(16.dp)
    ) {
        currentQuestion?.let {
            QuestionCard(
                question = it,
                onYesClick = { viewmodel.answerQuestion(it.gejalaCode, 0.6) },
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

    Column {
        Text(
            text = "Hasil Tes:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.Black


        )
        results.forEach { result ->
            Text(
                text = "${result.penyakit} : ${"%.2f".format(result.cf)}%",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Black

            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        highestResult?.let {
            if (it.cf > 0){
                Text(
                    text = "Kesimpulan: ${it.penyakit} dengan tingkat kepercayaan ${"%.2f".format(it.cf)}%",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.Black
                )
            }else{
                Text(
                    text = "Tidak ada jurusan yang cocok",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.Black
                )
            }
        }

        Button(
            onClick = {
                viewModel.saveDiagnoseResult(context, petName)
                navController.navigate(Graph.SCREEN_HISTORY)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun QuestionCard(question: Gejala, onYesClick: () -> Unit, onNoClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = question.gejalaName, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onYesClick,
                    colors = ButtonDefaults.buttonColors(Tombol)) {
                    Text(text = "Ya")
                }
                Button(
                    onClick = onNoClick,
                    colors = ButtonDefaults.buttonColors(Tombol)) {
                    Text(text = "Tidak")
                }
            }
        }
    }
}


@Preview()
@Composable
fun PreviewQuestionView() {
    CatcareexpertsystemTheme {
        QuestionScreen(rememberNavController())
    }
}