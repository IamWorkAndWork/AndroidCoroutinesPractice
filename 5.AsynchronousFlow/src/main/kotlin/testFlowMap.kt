import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.System.currentTimeMillis

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

fun main() = runBlocking<Unit> {
//    val startTime = currentTimeMillis() // remember the start time
//    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
//        .flatMapLatest { requestFlow(it) }
//        .collect { value -> // collect and print
//            println("$value at ${currentTimeMillis() - startTime} ms from start")
//        }
    val data = mutableListOf<Int>()
    (1..3).asFlow()
//        .flatMapMerge { value ->
//            requestFlow(value)
//        }
        .onCompletion { er ->
            println("data = ${data.toString()}")
        }
        .collect {
            data.add(it)
//            println("result = $it")
        }

}