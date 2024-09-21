package com.example.unitconverter


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Metres") }
    var outputUnit by remember { mutableStateOf("Metres") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oconversionFactor = remember { mutableStateOf(1.0) }

    val customTextStyle = TextStyle(

        fontFamily = FontFamily.Serif,
        fontSize = 32.sp,
        color = Color.Red
    )

    fun converterUnits(){
        // ?: -elvis operator(simple way of if statement)
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all the UI elements will stacked in downwards direction
        // Display the title
        Text(text = "Unit Converter",
            style = customTextStyle
            )
        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for user input
        OutlinedTextField(
            value = inputValue,
            onValueChange ={
                inputValue=it
                converterUnits()
            //here goes what should happen, when the OutlinedTextField value changes
             },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Here all the UI elements will stacked next to each other
            //Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimetre") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimetre"
                            conversionFactor.value = 0.01
                            converterUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Metre") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Metre"
                            conversionFactor.value = 1.0
                            converterUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            converterUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Millimetre") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimetre"
                            conversionFactor.value = 0.001
                            converterUnits()
                        })

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimetre") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimetre"
                            oconversionFactor.value = 0.01
                            converterUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Metre") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Metre"
                            oconversionFactor.value = 1.0
                            converterUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oconversionFactor.value = 0.3048
                            converterUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Millimetre") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimetre"
                            oconversionFactor.value = 0.001
                            converterUnits()
                        })

                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        //Result Text
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )


    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}