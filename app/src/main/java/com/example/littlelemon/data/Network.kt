package com.example.littlelemon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)

/** SAMPLE JSON TO FETCH
 *   "menu": [
 *     {
 *       "id": 1,
 *       "title": "Greek Salad",
 *       "description": "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
 *       "price": "10",
 *       "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
 *       "category": "starters"
 *     },
 */
@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Int,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String
) {
    fun toMenuItemRoom(): MenuItemRoom {
        return MenuItemRoom(
            id,
            title,
            description,
            price,
            image,
            category
        )
    }
}