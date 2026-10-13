package com.example.culculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.culculator.ui.theme.CulculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Result: 0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Simple Calculator", fontSize = 35.sp, color = Color.Magenta,fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 20.dp))

        // Two EditText equivalent
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Enter first number") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Enter second number") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
        )

        Text(
            text = result,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        // Buttons for +, -, x, /
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { result = calculate(num1, num2, '+') }) { Text("+") }
            Button(onClick = { result = calculate(num1, num2, '-') }) { Text("-") }
            Button(onClick = { result = calculate(num1, num2, '*') }) { Text("×") }
            Button(onClick = { result = calculate(num1, num2, '/') }) { Text("÷") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                num1 = ""
                num2 = ""
                result = "Result: 0"
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear")
        }
    }
}

fun calculate(s1: String, s2: String, op: Char): String {
    if (s1.isEmpty() || s2.isEmpty()) return "Please enter valid numbers"
    try {
        val n1 = s1.toDouble()
        val n2 = s2.toDouble()
        if (op == '/' && n2 == 0.0) return "Cannot divide by zero"
        val res = when (op) {
            '+' -> n1 + n2
            '-' -> n1 - n2
            '*' -> n1 * n2
            '/' -> n1 / n2
            else -> 0.0
        }
        return "Result: $res"
    } catch (e: Exception) {
        return "Please enter valid numbers"
    }
}