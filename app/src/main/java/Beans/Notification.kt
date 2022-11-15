package Beans

class Notification {

    constructor(id: Int, title: String, description: String, consultId: Int) {
        this.id = id
        this.title = title
        this.description = description
        this.consultId = consultId
    }

    var id:Int
    var title:String
    var description: String
    var consultId:Int

}