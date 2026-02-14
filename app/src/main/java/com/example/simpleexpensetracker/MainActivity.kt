package com.example.simpleexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleexpensetracker.presentation.ExpenseViewModel
import com.example.simpleexpensetracker.ui.theme.SimpleExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleExpenseTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel= viewModels<ExpenseViewModel>().value
                    Greeting(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: ExpenseViewModel, modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Column(
        modifier= Modifier.padding(15.dp),

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value=text,
                onValueChange = {text=it}
                ,modifier= Modifier.weight(1f),
                label = { Text("Title") }
            )
            OutlinedTextField(
                value=amount,
                onValueChange = {amount=it}
                ,modifier= Modifier.weight(1f),
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier= Modifier.width(8.dp))
            Button(onClick = {viewModel.addExpense(text,amount.toDoubleOrNull())
                text=""
                amount=""}) {
                Text(text="Add")

            }
        }
        Spacer(modifier= Modifier.height(16.dp))
        LazyColumn {
            items(viewModel.expenses){
                expense ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()

                ){
                    Text(text=expense.title,modifier=Modifier.weight(1f)
                        , textAlign = TextAlign.Center)
                    Spacer(modifier= Modifier.width(8.dp))
                    Text(expense.amount.toString(),modifier=Modifier.weight(1f),
                        textAlign = TextAlign.Center)
                    Spacer(modifier= Modifier.width(8.dp))
                    Button(onClick = {viewModel.deleteAmount(expense)}) {
                        Text("Delete")
                    }
                }
            }

        }
        Spacer(modifier= Modifier.height(20.dp))
        Text(viewModel.getTotal().toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold,
            modifier= Modifier.align(Alignment.CenterHorizontally)
                )

    }
}
