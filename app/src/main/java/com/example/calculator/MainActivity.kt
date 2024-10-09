package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.colorResource
import com.example.calculator.ui.theme.CalculatorTheme
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorLayout()
                }
            }
        }
    }
}

@Composable
fun CalculatorLayout() {
    var displayText by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf("") }
    var operand2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = displayText,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))) {
            CalculatorButton(text = "AC") {
                displayText = "0"
                operand1 = ""
                operand2 = ""
                operator = ""
            }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "C") {
                displayText = displayText.dropLast(1).ifEmpty { "0" }
            }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "%") {
                displayText = (displayText.toDouble() / 100).toString()
            }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "/") {
                operator = "/"
                operand1 = displayText
                displayText = "0"
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))) {
            CalculatorButton(text = "7") { appendNumber("7", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "8") { appendNumber("8", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "9") { appendNumber("9", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "*") {
                operator = "*"
                operand1 = displayText
                displayText = "0"
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))) {
            CalculatorButton(text = "4") { appendNumber("4", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "5") { appendNumber("5", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "6") { appendNumber("6", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "-") {
                operator = "-"
                operand1 = displayText
                displayText = "0"
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))) {
            CalculatorButton(text = "1") { appendNumber("1", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "2") { appendNumber("2", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "3") { appendNumber("3", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "+") {
                operator = "+"
                operand1 = displayText
                displayText = "0"
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))) {
            CalculatorButton(text = "0") { appendNumber("0", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = ".") { appendNumber(".", displayText) { displayText = it } }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "=") {
                operand2 = displayText
                displayText = calculateResult(operand1, operand2, operator)
            }
        }
    }
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(
                width = dimensionResource(id = R.dimen.buttonWidth),
                height = dimensionResource(id = R.dimen.buttonHeight)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.buttonBackground)
        ),
        shape = CircleShape
    ) {
        Text(
            text = text,
            fontSize = dimensionResource(id = R.dimen.buttonTextSize).value.sp,
            color = colorResource(id = R.color.buttonText)
        )
    }
}

fun appendNumber(number: String, currentDisplay: String, updateDisplay: (String) -> Unit) {
    if (currentDisplay == "0") {
        updateDisplay(number)
    } else {
        updateDisplay(currentDisplay + number)
    }
}

fun calculateResult(operand1: String, operand2: String, operator: String): String {
    return try {
        val op1 = operand1.toDouble()
        val op2 = operand2.toDouble()
        when (operator) {
            "+" -> (op1 + op2).toString()
            "-" -> (op1 - op2).toString()
            "*" -> (op1 * op2).toString()
            "/" -> (op1 / op2).toString()
            "%" -> (op1 / 100).toString()
            else -> "Error"
        }
    } catch (e: Exception) {
        "Error"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorTheme {
        CalculatorLayout()
    }
}