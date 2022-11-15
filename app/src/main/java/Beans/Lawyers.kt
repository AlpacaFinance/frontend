package Beans

class Lawyers {
    var id:Int
    var fisrtName:String
    var lastName:String
    var email:String
    var password:String
    var description:String
    var urlImage:String
    var type:String

    constructor(
        id: Int,
        fisrtName: String,
        lastName: String,
        email: String,
        password: String,
        description: String,
        urlImage: String,
        type: String,
        specialty: String,
        wonCases: Int,
        totalCases: Int,
        lostCases: Int
    ) {
        this.id = id
        this.fisrtName = fisrtName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.description = description
        this.urlImage = urlImage
        this.type = type
        this.specialty = specialty
        this.wonCases = wonCases
        this.totalCases = totalCases
        this.lostCases = lostCases
    }

    var specialty:String
    var wonCases:Int
    var totalCases:Int
    var lostCases:Int

}