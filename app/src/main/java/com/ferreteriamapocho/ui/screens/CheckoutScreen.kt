package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ferreteriamapocho.data.Order
import com.ferreteriamapocho.data.OrderDatabase
import com.ferreteriamapocho.data.OrderRepository
import com.ferreteriamapocho.data.Repo
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(nav: NavHostController) {
    val context = LocalContext.current
    val db = OrderDatabase.getDatabase(context)
    val repo = OrderRepository(db.orderDao())
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(1) }
    val product = Repo.products.firstOrNull()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Finalizar compra", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        if (product != null) {
            Text("Producto: ${product.name}")
            Text("Precio: $ ${"%,.0f".format(product.price)}")
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Dirección de entrega") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (product != null && name.isNotBlank() && address.isNotBlank()) {
                    scope.launch {
                        repo.insert(
                            Order(
                                productName = product.name,
                                price = product.price,
                                quantity = quantity,
                                customerName = name,
                                address = address
                            )
                        )
                    }
                    nav.navigate("orders")
                }
            },
            enabled = name.isNotBlank() && address.isNotBlank()
        ) {
            Text("Confirmar pedido")
        }
    }
}
