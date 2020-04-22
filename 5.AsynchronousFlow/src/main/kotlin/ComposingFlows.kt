import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    //    zip()

    combine()

}

suspend fun combine() {

    val numbers = (1..5).asFlow().onEach {
        delay(300L)
    }
    val values = flowOf("One", "Two", "Three", "Four", "Five")
        .onEach {
            delay(400L)
        }
    numbers.combine(values) { a, b ->
        "$a -> $b"
    }.collect {
        println(it)
    }
}

suspend fun zip() {

    val English = flowOf("One", "Two", "Three")
        .onEach {
            delay(100L)
        }
    val Thai = flowOf("หนึ่ง", "สอง", "สาม")
        .onEach {
            delay(400L)
        }

    English.zip(Thai) { en, th ->
        "$en in Thai is $th"
    }.collect {
        println(it)
    }

}


