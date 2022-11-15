package Beans.service.`class`

import java.io.Serializable

data class Lawyer(
    val description: String,
    val email: String,
    val fisrtName: String,
    val id: Int,
    val lastName: String,
    val password: String,
    val type: String,
    val urlImage: String
): Serializable