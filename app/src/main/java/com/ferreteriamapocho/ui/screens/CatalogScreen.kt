package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CatalogScreen(nav: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Catálogo de productos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Martillo Stanley", fontWeight = FontWeight.SemiBold)
                    Text("$ 9.990", style = MaterialTheme.typography.titleSmall)
                }
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Agregar al carrito")
                }
                    Text("Taladro Black & Decker 12V", fontWeight = FontWeight.SemiBold)
                    Text("$ 45.990", style = MaterialTheme.typography.titleSmall)
                }
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Agregar al carrito")
                }

            }
        }
    }

