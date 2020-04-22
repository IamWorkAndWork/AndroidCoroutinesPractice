import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow

fun main() {

    runBlocking {

        val job = launch {

            val numberFLow = sendNewNumbers()
            println("Flow hasn't started yet")
            println("Starting flow now")

            numberFLow.collectIndexed { index, value ->
                println("index #$index = $value")
            }
        }

        delay(1100L)
        job.cancel()

    }


}

fun sendNewNumbers() = flow {
    listOf<Int>(1, 2, 3).forEach {
        delay(500L)
        emit(it)
    }
}
