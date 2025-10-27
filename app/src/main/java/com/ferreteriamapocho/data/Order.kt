package com.ferreteriamapocho.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val customerName: String,
    val address: String
)
