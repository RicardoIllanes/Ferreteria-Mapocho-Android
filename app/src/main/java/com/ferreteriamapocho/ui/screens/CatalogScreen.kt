package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ferreteriamapocho.data.Product
import com.ferreteriamapocho.ui.theme.Orange80
import com.ferreteriamapocho.ui.theme.White
import com.ferreteriamapocho.R

@Composable
fun CatalogScreen(navController: NavHostController) {

    // 🔹 Lista de productos (ejemplo)
    val products = listOf(
        Product(1, "Taladro inalámbrico Bosch", "SKU1234", 59990.0, 10),
        Product(2, "Sierra circular Makita", "SKU2345", 89990.0, 8),
        Product(3, "Martillo Stanley", "SKU3456", 15990.0, 25),
        Product(4, "Set de llaves combinadas", "SKU4567", 19990.0, 15),
        Product(5, "Cinta métrica 5m", "SKU5678", 4990.0, 50),
        Product(6, "Caja de herramientas metálica", "SKU6789", 29990.0, 5)
    )

    // 🔹 Imagenes asociadas (por ID)
    val productImages = mapOf(
        1 to R.drawable.taladro,
        2 to R.drawable.sierra,
        3 to R.drawable.martillo,
        4 to R.drawable.llaves,
        5 to R.drawable.cinta,
        6 to R.drawable.caja
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo Ferretería", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Orange80,
                    titleContentColor = White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("product/${product.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        val imageRes = productImages[product.id] ?: R.drawable.placeholder
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 12.dp)
                        )

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
