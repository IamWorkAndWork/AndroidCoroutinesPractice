package test

class testList2 {
}

fun main() {

    var dataList = arrayListOf<String?>()

    val data = dataList.getOrNull(0) ?: "hello"
    println("data = $data")

    val data2 = dataList.firstOrNull() ?: "hello2"
    println("data2 = $data2")

    val data3 = dataList.getOrElse(0) { "hello3" }
    println("data3 = $data3")

//    val data = dataList[0] ?: ""
//    println("data = " + data)
//
//    //solution1
//    val category = if (dataList.size > 0) {
//        dataList[0] ?: "1"
//    } else {
//        "hello"
//    }
//    println("category = " + category)
//
//    //solutionh2
//    val category2 = if (dataList.isNotEmpty()) {
//        dataList[0] ?: "1"
//    } else {
//        "hello2"
//    }
//    println("category2 = " + category2)
}