package com.example.unipath.diagnose

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.unipath.ui.theme.ButtonPrimary
import com.example.unipath.ui.theme.CatcareexpertsystemTheme
import com.example.unipath.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputpetNameScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewmodel: DiagnoseViewmodel = viewModel()
    var text by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Bg,
                    scrolledContainerColor = Primary,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "back button"
                    )
                },
                title = {
                    Text(
                        text = "Mulai Tes",
                        style = MaterialTheme.typography.headlineMedium.copy(color = Color.Black),
                    )
                })
        }
    ) {
                Column(modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Bg)
                    .padding(30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,)
                {

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = Primary,
                            focusedTextColor = Primary,
                            focusedLabelColor = Primary,
                        ),
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Nama Anda") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        viewmodel.clearResults()
                        savePetName(context,text,navController)
                                     }, colors = ButtonDefaults.buttonColors( ButtonPrimary), modifier = Modifier.align(Alignment.End)) {
                        Text(text = "Lanjut", color = Color.White)
                    }
                }


    }
}
fun savePetName(context: Context, petName: String, navController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences("PetPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("temp_pet_name", petName)
    editor.apply()
    navController.navigate(Graph.SCREEN_QUESTION)
}
@Preview
@Composable
private fun InputFormPet() {
    CatcareexpertsystemTheme {
        InputpetNameScreen(rememberNavController())
    }

}