import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.*

fun main() {
    runBlocking {

        flowOf("one", 1, true)
            .collect {
                println("flowOf = " + it)
            }

        createFlowEmit().collect {
            println("Received way1 : $it")
        }
        println()
        createFlowFromAsFlow().collect {
            println("Received way2 : $it")
        }
        println()
        createFlowFromFlowOf()
            .collect {
                println("Received way3 : $it")
            }
    }
}

fun createFlowEmit() = flow<Int> {
    for (i in 1..3) {
        delay(300L) //delay for 300ms to emit next number
        emit(i)              //emit next value
    }
}

fun createFlowFromAsFlow(): Flow<Int> {
    return listOf<Int>(1, 2, 3).asFlow()
        .onEach {
            delay(300L)
        }
        .flowOn(Dispatchers.IO)
}

fun createFlowFromFlowOf(): Flow<Int> {
    return flowOf(1, 2, 3)
}