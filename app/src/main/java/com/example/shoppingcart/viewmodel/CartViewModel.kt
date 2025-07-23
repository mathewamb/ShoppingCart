package com.example.shoppingcart.viewmodel



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.data.Product
import com.example.shoppingcart.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>(repository.getProducts())
    val products: LiveData<List<Product>> = _products

    private val _cartItems = MutableLiveData<List<Product>>(emptyList())
    val cartItems: LiveData<List<Product>> = _cartItems

    val totalPrice: LiveData<Double> = MutableLiveData<Double>().apply {
        _cartItems.observeForever { items ->
            value = items.sumOf { it.price }
        }
    }

    fun addToCart(product: Product) {
        _cartItems.value = _cartItems.value?.plus(product)
    }
}

