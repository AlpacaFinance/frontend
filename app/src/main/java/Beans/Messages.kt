package Beans

class Messages {
    constructor(id: Int, text: String, image: String, type: String) {
        this.id = id
        this.text = text
        this.image = image
        this.type = type
    }

    var id:Int
    var text:String
    var image:String
    var type:String
}