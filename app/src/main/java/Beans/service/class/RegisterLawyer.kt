package Beans.service.`class`

data class RegisterLawyer (
    val fisrtName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val description: String,
    val urlImage: String,
    val type: String,
    val specialty: String,
    val wonCases: Int,
    val totalCases: Int,
    val lostCases: Int
)