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
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.toString

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
    var expression by remember { mutableStateOf("") }
    var lastNumber by remember { mutableStateOf("") }
    var resetDisplay by remember { mutableStateOf(false) } // Controla si se debe resetear el display

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = expression,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.End
        )
        Text(
            text = displayText,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            CalculatorButton(text = "AC") {
                displayText = "0"
                expression = ""
                lastNumber = ""
                resetDisplay = false
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "C") {
                displayText = displayText.dropLast(1).ifEmpty { "0" }
                expression = expression.dropLast(1)
                lastNumber = lastNumber.dropLast(1)
                resetDisplay = false
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "%") {
                if (expression.isNotEmpty() && !isOperator(expression.last())) {
                    expression += "%"
                    resetDisplay = true
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "/") {
                if (expression.isNotEmpty() && !isOperator(expression.last())) {
                    expression += "/"
                    resetDisplay = true
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            CalculatorButton(text = "7") {
                resetDisplay = appendNumber("7", displayText, resetDisplay) { displayText = it; expression += "7"; lastNumber += "7" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "8") {
                resetDisplay = appendNumber("8", displayText, resetDisplay) { displayText = it; expression += "8"; lastNumber += "8" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "9") {
                resetDisplay = appendNumber("9", displayText, resetDisplay) { displayText = it; expression += "9"; lastNumber += "9" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "*") {
                if (expression.isNotEmpty() && !isOperator(expression.last())) {
                    expression += "*"
                    resetDisplay = true
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            CalculatorButton(text = "4") {
                resetDisplay = appendNumber("4", displayText, resetDisplay) { displayText = it; expression += "4"; lastNumber += "4" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "5") {
                resetDisplay = appendNumber("5", displayText, resetDisplay) { displayText = it; expression += "5"; lastNumber += "5" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "6") {
                resetDisplay = appendNumber("6", displayText, resetDisplay) { displayText = it; expression += "6"; lastNumber += "6" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "-") {
                if (expression.isNotEmpty() && !isOperator(expression.last())) {
                    expression += "-"
                    resetDisplay = true
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            CalculatorButton(text = "1") {
                resetDisplay = appendNumber("1", displayText, resetDisplay) { displayText = it; expression += "1"; lastNumber += "1" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "2") {
                resetDisplay = appendNumber("2", displayText, resetDisplay) { displayText = it; expression += "2"; lastNumber += "2" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "3") {
                resetDisplay = appendNumber("3", displayText, resetDisplay) { displayText = it; expression += "3"; lastNumber += "3" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "+") {
                if (expression.isNotEmpty() && !isOperator(expression.last())) {
                    expression += "+"
                    resetDisplay = true
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            CalculatorButton(text = "0") {
                resetDisplay = appendNumber("0", displayText, resetDisplay) { displayText = it; expression += "0"; lastNumber += "0" }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = ".") {
                resetDisplay = appendNumber(".", displayText, resetDisplay) { displayText = it; expression += "."; lastNumber += "." }
            }
            Spacer(modifier = Modifier.width(16.dp))
            CalculatorButton(text = "=") {
                displayText = calculateResult(expression)
                expression = displayText
                lastNumber = displayText
                resetDisplay = true
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

fun appendNumber(number: String, currentDisplay: String, resetDisplay: Boolean, updateDisplay: (String) -> Unit): Boolean {
    if (currentDisplay == "0" || resetDisplay) {
        updateDisplay(number)
        return false
    } else {
        updateDisplay(currentDisplay + number)
        return resetDisplay
    }
}


fun isOperator(char: Char): Boolean {
    return char == '+' || char == '-' || char == '*' || char == '/' || char == '%'
}

fun calculateResult(expression: String): String {
    return try {
        // Reemplaza los porcentajes con su valor decimal
        val modifiedExpression = expression.replace(Regex("(\\d+)%")) {
            (it.groupValues[1].toDouble() / 100).toString()
        }
        val result = ExpressionBuilder(modifiedExpression).build().evaluate()
        result.toString()
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