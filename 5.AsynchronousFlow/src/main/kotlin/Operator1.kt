import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        //        transformOperator()

//        mapOperator()

        filterOperator()

    }
}

suspend fun transformOperator() {
    //Can emit any value at any point
    (1..10).asFlow()
        .transform {
            emit("Emitting string value $it")
            emit(it)
        }
        .collect {
            println(it)
        }
}

suspend fun mapOperator() {
    listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 910).asFlow()
        .map {
            delay(500L)
            "Number $it"
        }
        .collect {
            println(it)
        }
}

suspend fun filterOperator() {
    (1..10).asFlow()
        .filter {
            it % 2 == 0
        }
        .collect {
            println(it)
        }
}