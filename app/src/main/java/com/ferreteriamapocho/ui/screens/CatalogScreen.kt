package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ferreteriamapocho.data.Product
import com.ferreteriamapocho.data.UserPrefs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavHostController) {
    val context = LocalContext.current
    val userProfile by UserPrefs.profileFlow(context).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Ferretería Mapocho", fontWeight = FontWeight.Bold)
                        userProfile?.name?.takeIf { it.isNotBlank() }?.let {
                            Text("Hola, $it 👋", color = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val products = listOf(
                Product(1, "Taladro inalámbrico Bosch", "SKU1234", 59990.0, 10),
                Product(2, "Sierra circular Makita", "SKU2345", 89990.0, 8),
                Product(3, "Martillo Stanley", "SKU3456", 15990.0, 25),
                Product(4, "Set de llaves combinadas", "SKU4567", 19990.0, 15),
                Product(5, "Cinta métrica 5m", "SKU5678", 4990.0, 50)
            )

            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("product/${product.id}") },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(Modifier.weight(1f)) {
                            Text(product.name, fontWeight = FontWeight.Bold)
                            Text("Precio: $${"%,.0f".format(product.price)}")
                            Text("Stock: ${product.stock}")
                        }

                        Button(onClick = {
                            navController.navigate("product/${product.id}")
                        }) {
                            Text("Ver")
                        }
                    }
                }
            }
        }
    }
}
