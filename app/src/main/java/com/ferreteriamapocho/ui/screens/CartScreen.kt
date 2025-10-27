package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ferreteriamapocho.data.Product

@Composable
fun CartScreen(nav: NavHostController) {
    var cart by remember { mutableStateOf(listOf<Product>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Carrito de compras",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        if (cart.isEmpty()) {
            Text("Aún no hay productos en tu carrito 🛠️")
            Spacer(Modifier.height(20.dp))
            Button(onClick = { nav.navigate("catalog") }) {
                Text("Ir al catálogo")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(cart) { product ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(product.name, fontWeight = FontWeight.SemiBold)
                                Text("Precio: $${"%,.0f".format(product.price)}")
                            }
                            Button(onClick = {
                                cart = cart - product
                            }) {
                                Text("Quitar")
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            val total = cart.sumOf { it.price }
            Text("Total: $${"%,.0f".format(total)}", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                nav.navigate("checkout/${cart.first().id}")
            }) {
                Text("Proceder al pago")
            }
        }
    }
}
