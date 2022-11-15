package Beans.service.`class`

data class CasoItem(
    val title: String,
    val client: Client?, // OBJETO
    val description: String,
    val lawyer: Lawyer?, //OBJETO
    val state: String?
)