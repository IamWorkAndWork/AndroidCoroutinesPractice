import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    //    onCompletion()

//    cache()

    tryCache()

}


suspend fun tryCache() {
    try {
        (1..3).asFlow()
            .onEach {
                check(it != 2)
            }
            .collect {
                println(it)
            }
    } catch (error: Exception) {
        println("Caught exception $error")
    }
}

//suspend fun tryCatch() {
//    try {
//        (1..3).asFlow()
//            .onEach { check(it != 2) }
//            .collect { println(it) }
//    } catch (e: Exception) {
//        println("Caught exception $e")
//    }
//}


suspend fun cache() {
    (1..3).asFlow()
        .onEach {
            check(it != 2)
        }
        .catch { error ->
            println("Caught exception $error")
        }
        .collect {
            println(it)
        }
}

suspend fun onCompletion() {
    (1..3).asFlow()
        .onEach {
            check(it != 2)
        }
        .catch { error ->
            println("Caught exception : $error")
        }
        .onCompletion { error ->
            if (error == null)
                println("Flow completed successfully")
            else
                println("Flow completed with exception : $error")
        }
        .collect {
            println(it)
        }
}

