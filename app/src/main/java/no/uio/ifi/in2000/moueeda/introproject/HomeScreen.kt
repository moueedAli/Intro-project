package no.uio.ifi.in2000.moueeda.introproject

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    var fact by remember { mutableStateOf("Laster...") }
    var number by remember { mutableStateOf("42") } // Standardnummer ved start

    LaunchedEffect(Unit) { APIClient.fetchNumberFact(number.toInt(), { fact = it ?: "Kunne ikke hente data" }) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = number,
            onValueChange = { newValue -> number = newValue.filter { it.isDigit() } },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(8.dp)) {
                    if (number.isEmpty()) Text("Skriv inn et tall")
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val num = number.toIntOrNull() ?: 42 // Standard til 42 hvis input er tom
            APIClient.fetchNumberFact(num) { fact = it ?: "Kunne ikke hente data" }
        }) {
            Text("Hent fakta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = fact, style = MaterialTheme.typography.headlineMedium)
    }
}



