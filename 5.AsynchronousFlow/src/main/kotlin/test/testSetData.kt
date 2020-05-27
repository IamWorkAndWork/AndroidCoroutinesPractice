package test

private var topBarNonTrueUrl = ""

data class testModel(val url: String? = null, val url2: String? = "")

fun main() {

    var ddd = ""

    val data = testModel(null, ddd)

    topBarNonTrueUrl = data.url.orEmpty()//pass

    topBarNonTrueUrl = data.url.orEmpty()//error

    topBarNonTrueUrl = data.url2.orEmpty()

    topBarNonTrueUrl = data.url.orEmpty()

//    data.url?.let {
//        topBarNonTrueUrl = it
//    }
//
//    data.url2?.let {
//        topBarNonTrueUrl = it
//    }
}