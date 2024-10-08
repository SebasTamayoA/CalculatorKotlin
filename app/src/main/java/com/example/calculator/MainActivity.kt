package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    // Crear la disposición de los botones de números y operadores
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pantalla para mostrar resultados y números
        Text(
            text = "0", // Aquí puedes enlazar el estado para mostrar los números y resultados
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        // Línea adicional (AC, borrar, %, /)
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))
        ) {
            CalculatorButton(text = "AC")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "C")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "%")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "/")
        }
        // Primera fila (7, 8, 9, /)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))
        ) {
            CalculatorButton(text = "7")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "8")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "9")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "*")
        }
        // Segunda fila (4, 5, 6, *)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))
        ) {
            CalculatorButton(text = "4")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "5")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "6")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "-")
        }
        // Tercera fila (1, 2, 3, -)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))
        ) {
            CalculatorButton(text = "1")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "2")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "3")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "+")
        }
        // Cuarta fila (0, =, +)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.buttonPadding)))
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.buttonPadding))
        ) {
            CalculatorButton(text = "0")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = ".")
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.buttonPadding)))
            CalculatorButton(text = "=")
        }
    }
}

@Composable
fun CalculatorButton(text: String) {
    Button(
        onClick = { /* No funcional */ },
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorTheme {
        CalculatorLayout()
    }
}