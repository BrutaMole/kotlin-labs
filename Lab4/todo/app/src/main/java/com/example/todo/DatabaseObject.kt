package com.example.todo
object DatabaseObject {
    var list_data = mutableListOf<ObjectData>()
    fun AddData(title: String , desc: String) {
        list_data.add(ObjectData(title, desc))
    }
    fun completeData(i:Int){
        list_data.removeAt(i)
    }
    fun getshowAllData(): List<ObjectData> {
        return list_data
    }
    fun getshowData(i:Int): ObjectData {
        return list_data[i]
    }
}