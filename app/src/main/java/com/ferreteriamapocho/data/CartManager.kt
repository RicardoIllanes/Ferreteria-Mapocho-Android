package com.ferreteriamapocho.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CartManager {
    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems

    fun addToCart(product: Product) {
        _cartItems.value = _cartItems.value + product
    }

    fun removeFromCart(product: Product) {
        _cartItems.value = _cartItems.value - product
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
    fun total(): Double {
        return _cartItems.value.sumOf { it.price }
    }
}
