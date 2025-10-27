package com.ferreteriamapocho.data

class OrderRepository(private val dao: OrderDao) {
    suspend fun insert(order: Order) = dao.insert(order)
    fun getAllOrders() = dao.getAllOrders()
}
