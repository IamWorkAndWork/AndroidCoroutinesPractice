import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {

    runBlocking {

        val firstDeferred = async {
            getFirstValue()
        }
        val secondDeferred = async {
            getSecondValue()
        }

        println("Doing some processing here")
        delay(500L)
        println("Waiting For Values")

        val firstValue = firstDeferred.await()
        val secondValue = secondDeferred.await()

        println("The total is = ${firstValue + secondValue}")

    }

}

suspend fun getFirstValue(): Int {
    delay(1000L)
    val value = Random.nextInt(100)
    println("Returning first value = $value")
    return value
}


suspend fun getSecondValue(): Int {
    delay(2000L)
    val value = Random.nextInt(1000)
    println("Returning second value $value")
    return value
}