package com.ferreteriamapocho.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.ferreteriamapocho.data.UserPrefs
import com.ferreteriamapocho.ui.screens.*

sealed class Dest(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Catalog : Dest("catalog", "Catálogo", Icons.Filled.List)
    data object Cart : Dest("cart", "Carrito", Icons.Filled.ShoppingCart)
    data object Orders : Dest("orders", "Pedidos", Icons.Filled.List)
    data object Account : Dest("account", "Cuenta", Icons.Filled.Person)

    companion object {
        val bottom = listOf(Catalog, Cart, Orders, Account)
    }
}

@Composable
fun AppRoot() {
    val nav = rememberNavController()
    val context = LocalContext.current
    val userProfile by UserPrefs.profileFlow(context).collectAsState(initial = null)

    Scaffold(
        bottomBar = {
            val backStackEntry by nav.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            Column {
                NavigationBar {
                    Dest.bottom.forEach { d ->
                        NavigationBarItem(
                            selected = currentRoute == d.route,
                            onClick = { nav.navigate(d.route) },
                            icon = { Icon(d.icon, contentDescription = d.label) },
                            label = { Text(d.label) }
                        )
                    }
                }

                // 👋 Saludo debajo de la barra
                userProfile?.name?.takeIf { it.isNotBlank() }?.let {
                    Text(
                        text = "Bienvenido, $it 👋",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = "login", //
            modifier = Modifier.padding(padding)
        ) {
            composable("login") { LoginScreen(nav) }

            composable(Dest.Catalog.route) { CatalogScreen(nav) }
            composable(Dest.Cart.route) { CartScreen(nav) }
            composable(Dest.Orders.route) { OrdersScreen() }
            composable(Dest.Account.route) { AccountScreen() }

            composable("product/{productId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: -1
                ProductDetailScreen(id, nav)
            }

            composable("checkout/{productId}") { CheckoutScreen(nav) }
        }
    }
}
