import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {

    runBlocking {
        val time = measureTimeMillis {
            createBufferFlow()
                .buffer()
                .collect {
                    delay(300L)
                    println(it)
                }
        }
        println("Collected in $time ms")

    }

}

fun createBufferFlow() = flow<Int> {
    (1..3).forEach {
        delay(100L)
        emit(it)
    }
}
