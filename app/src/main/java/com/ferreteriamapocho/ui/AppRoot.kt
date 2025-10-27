package com.ferreteriamapocho.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.ferreteriamapocho.ui.screens.*

sealed class Dest(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Catalog : Dest("catalog", "Catálogo", Icons.Filled.List)
    data object Cart : Dest("cart", "Carrito", Icons.Filled.ShoppingCart)
    data object Orders : Dest("orders", "Pedidos", Icons.Filled.List)
    data object Account : Dest("account", "Cuenta", Icons.Filled.Person)
    companion object { val bottom = listOf(Catalog, Cart, Orders, Account) }
}

@Composable
fun AppRoot() {
    val nav = rememberNavController()

    Scaffold(
        bottomBar = {
            val backStackEntry by nav.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
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
        }
    ) { padding ->
        NavHost(navController = nav, startDestination = Dest.Catalog.route, modifier = Modifier.padding(padding)) {
            composable(Dest.Catalog.route) { CatalogScreen(nav) }
            composable(Dest.Cart.route) { CartScreen(nav) }
            composable(Dest.Orders.route) { OrdersScreen() }
            composable(Dest.Account.route) { AccountScreen() }
            composable("checkout/{productId}") { CheckoutScreen(nav) }
        }
    }
}
