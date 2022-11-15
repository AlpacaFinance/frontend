package Beans

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OpenHelper(context: Context): SQLiteOpenHelper(context,"user.db",null,7) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table user(id integer primary key, firstName text, lastName text, email text," +
                " description text, urlImage text, type text, specialty text, wonCases integer, " +
                "totalCases integer, lostCases integer)"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "drop table user"
        p0!!.execSQL(query)
        onCreate(p0);
    }
    fun getUserType():String {
        val query = this.readableDatabase.rawQuery("select * from user", null)

        return if(query.moveToFirst()) {
            query.getString(6);
        } else {
            "";
        }
    }
    fun addUser(id:Int, firstName:String, lastName:String, email:String, description:String, urlImage:String,
                type:String, speciality: String, wonCases:Int, totalCases:Int, lostCases:Int) {

        if(getUserId() == -1) {
            val user = ContentValues()
            user.put("id", id.toInt())
            user.put("firstName", firstName.toString())
            user.put("lastName", lastName)
            user.put("email", email)
            user.put("description", description)
            user.put("urlImage", urlImage)
            user.put("type", type)
            user.put("specialty", speciality)
            user.put("wonCases", wonCases)
            user.put("totalCases", totalCases)
            user.put("lostCases", lostCases)

            println(user.get("email"))
            this.writableDatabase.insert("user", null, user)
        }

    }

    fun getUserId(): Int {
        val query = this.readableDatabase.rawQuery("select * from user", null)

        return if(query.moveToFirst()) {
            query.getInt(0);
        } else {
            -1;
        }
    }

    fun getUserName(): String {
        val query = this.readableDatabase.rawQuery("select firstName from user", null)
        val query2 = this.readableDatabase.rawQuery("select lastName from user", null)

        var res = ""

        if(query.moveToFirst()) {
            query.getString(0);
            res += "${query.getString(0)} ";
        } else {

        }

        if(query2.moveToFirst()) {
            query2.getString(0);
            res += "${query2.getString(0)}";
        } else {

        }

        return res
    }

    fun getNombre(): String {
        val query = this.readableDatabase.rawQuery("select firstName from user", null)

        return if(query.moveToFirst()) {
            query.getString(0);
        } else {
            "";
        }
    }

    fun getApellido(): String {
        val query = this.readableDatabase.rawQuery("select lastName from user", null)

        return if(query.moveToFirst()) {
            query.getString(0);
        } else {
            "";
        }
    }

    fun getUserDescription(): String {
        val query = this.readableDatabase.rawQuery("select description from user", null)

        return if(query.moveToFirst()) {
            query.getString(0);
        } else {
            "";
        }
    }

    fun getUserEmail(): String {
        val query = this.readableDatabase.rawQuery("select email from user", null)

        return if(query.moveToFirst()) {
            query.getString(0);
        } else {
            "";
        }
    }

    fun getUserImage(): String {
        val query = this.readableDatabase.rawQuery("select urlImage from user", null)

        return if(query.moveToFirst()) {
            query.getString(0);
        } else {
            "";
        }
    }

    fun updateUser(id:Int, firstname:String, lastname:String, desc:String, email:String) {
        this.writableDatabase!!.execSQL("UPDATE user SET firstname='${firstname}', lastname='${lastname}', description='${desc}', email='${email}' WHERE id=${id}")
    }

    fun removeUser(id:Int) {
        this.writableDatabase!!.execSQL("DELETE FROM user WHERE id=${id}")
    }


}