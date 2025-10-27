package com.ferreteriamapocho.data

object Repo {
    val products = listOf(
        Product(1, "Martillo Stanley 16 oz", "FER-001", 9990.0, 25),
        Product(2, "Taladro Black & Decker 12V", "FER-002", 45990.0, 15),
        Product(3, "Caja de clavos 2 (500 unidades)", "FER-003", 3990.0, 50),
        Product(4, "Cinta métrica 5m Truper", "FER-004", 2990.0, 30),
        Product(5, "Destornillador Philips Stanley", "FER-005", 2490.0, 40)
    )

    fun productById(id: Int): Product? = products.find { it.id == id }
}
