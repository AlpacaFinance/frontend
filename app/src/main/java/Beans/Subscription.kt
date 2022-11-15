package Beans

class Subscription {
    constructor(id: Int, name: String, description: String, price: Int) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
    }

    var id:Int
    var name:String
    var description:String
    var price:Int
}