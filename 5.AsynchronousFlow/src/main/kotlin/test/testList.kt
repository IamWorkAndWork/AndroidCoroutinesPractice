package test

fun main() {

    val arr = mutableListOf<String>()

    if (arr.isNullOrEmpty()) {
        println("1 1")
    } else {
        println("1 2")

    }

    val arr2: List<String>? = null

    if (arr2.isNullOrEmpty()) {
        println("2 1")
    } else {
        println("2 2")

    }

    val arr3 = mutableListOf<String>()
    arr3.add("1")
    if (arr3.isNullOrEmpty()) {
        println("3 1")
    } else {
        println("3 2")

    }

}
