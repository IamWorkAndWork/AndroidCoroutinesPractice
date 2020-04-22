import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {

        //        flowOnOperator()

//        reduceOperator()

        takeOperator()

    }

}

suspend fun flowOnOperator() {
    getLongtimeProcessIntList()
        .flowOn(Dispatchers.IO)
        .collect {
            println("data = $it")
        }
}

fun getLongtimeProcessIntList() = flow {
    (1..10).forEach {
        delay(300L)
        emit(it)
    }
}

suspend fun reduceOperator() {
    val size = 5
    val factorial = (1..size).asFlow()
        .reduce { accumulator, value ->
            accumulator * value
        }
    println("Factorial of $size is $factorial")
}

suspend fun takeOperator() {
    (1..10).asFlow()
        .take(2)
        .collect {
            println(it)
        }
}