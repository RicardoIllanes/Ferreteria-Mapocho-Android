package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun AccountScreen() {
    var name by remember { mutableStateOf("Ricardo Illanes") }
    var email by remember { mutableStateOf("ricardo@ferreteriamapocho.cl") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Mi cuenta", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Text("Nombre completo:")
        Text(name, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Text("Correo electrónico:")
        Text(email, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Divider()
        Spacer(Modifier.height(8.dp))

        Button(onClick = { /* Acción al hacer clic en "Cerrar sesión" */ }) {
            Text("Cerrar sesión")
        }
    }
}
