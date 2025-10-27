package com.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.ferreteriamapocho.data.Order
import com.ferreteriamapocho.data.OrderDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OrdersScreen() {
    val context = LocalContext.current
    val db = OrderDatabase.getDatabase(context)
    val scope = rememberCoroutineScope()

    var orders by remember { mutableStateOf(listOf<Order>()) }

    LaunchedEffect(Unit) {
        scope.launch {
            db.orderDao().getAllOrders().collectLatest {
                orders = it
            }
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Pedidos realizados",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        if (orders.isEmpty()) {
            Text("Aún no tienes pedidos guardados.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(orders) { order ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text("Producto: ${order.productName}", fontWeight = FontWeight.SemiBold)
                            Text("Cantidad: ${order.quantity}")
                            Text("Cliente: ${order.customerName}")
                            Text("Dirección: ${order.address}")
                            Text(
                                "Total: $ ${"%,.0f".format(order.price * order.quantity)}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
