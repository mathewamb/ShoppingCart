package com.example.shoppingcart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.shoppingcart.data.Product
import com.example.shoppingcart.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {
    val products by viewModel.products.observeAsState(emptyList())
    val cartItems by viewModel.cartItems.observeAsState(emptyList())
    val totalPrice by viewModel.totalPrice.observeAsState(0.0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Cart") },
                actions = {
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Cart: ${cartItems.size}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 8.dp))
                        Text("Total: $${String.format("%.2f", totalPrice)}", modifier = Modifier.padding(end = 8.dp))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Products", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))

            // 🟢 Use LazyColumn for the product list!
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) { product ->
                    ProductCard(product = product, onAdd = { viewModel.addToCart(product) })
                }
            }
        }
    }
}

// Helper composable for a product row
@Composable
fun ProductCard(product: Product, onAdd: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier.weight(1f)
            ) {
                Text(product.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text("Price: \$${product.price}", style = MaterialTheme.typography.bodyMedium)
            }
            Button(
                onClick = onAdd,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }
    }
}
