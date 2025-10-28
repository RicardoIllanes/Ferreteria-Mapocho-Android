package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ferreteriamapocho.data.Product
import com.ferreteriamapocho.data.CartManager
import com.ferreteriamapocho.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val products = listOf(
        Product(1, "Taladro inalámbrico Bosch", "SKU1234", 59990.0, 10),
        Product(2, "Sierra circular Makita", "SKU2345", 89990.0, 8),
        Product(3, "Martillo Stanley", "SKU3456", 15990.0, 25),
        Product(4, "Set de llaves combinadas", "SKU4567", 19990.0, 15),
        Product(5, "Cinta métrica 5m", "SKU5678", 4990.0, 50)
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de productos", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(Modifier.weight(1f)) {
                            Text(product.name, fontWeight = FontWeight.Bold)
                            Text("Precio: $${"%,.0f".format(product.price)}")
                            Text("Stock: ${product.stock}")
                        }

                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Button(
                                onClick = {
                                    CartManager.addToCart(product)
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "${product.name} agregado al carrito ✅",
                                            withDismissAction = true
                                        )
                                    }
                                }
                            ) {
                                Text("Agregar")
                            }

                            OutlinedButton(onClick = {
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
}
