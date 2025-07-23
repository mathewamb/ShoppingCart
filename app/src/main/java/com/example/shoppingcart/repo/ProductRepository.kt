package com.example.shoppingcart.repo



import com.example.shoppingcart.data.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {
    fun getProducts(): List<Product> = listOf(
        Product(1, "Apple", 1.49, "https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg"),
        Product(2, "Banana", 0.89, "https://images.pexels.com/photos/461208/pexels-photo-461208.jpeg"),
        Product(3, "Orange", 1.19, "https://images.pexels.com/photos/8373706/pexels-photo-8373706.jpeg"),
        Product(4, "Watermelon", 3.99, "https://images.pexels.com/photos/1313267/pexels-photo-1313267.jpeg"),
        Product(5, "Grapes", 2.99, "https://images.pexels.com/photos/708777/pexels-photo-708777.jpeg"),
        Product(6, "Pineapple", 2.49, "https://images.pexels.com/photos/174672/pexels-photo-174672.jpeg"),
        Product(7, "Strawberry", 2.59, "https://images.pexels.com/photos/11802768/pexels-photo-11802768.jpeg"),
        Product(8, "Blueberry", 3.19, "https://images.pexels.com/photos/21909299/pexels-photo-21909299.jpeg\n")
    )
}
