import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {

        val job1 = launch {
            //delay(3000L)
            println("Job1 launched")

            val job2 = launch {
                println("Job2 launched")
                delay(3000L)
                println("Job2 is finishing")
            }
            job2.invokeOnCompletion {
                println("Job2 Completed")
            }

            val job3 = launch {
                println("Job3 launched")
                delay(3000L)
                println("Job3 is finishing")
            }
            job3.invokeOnCompletion {
                println("Job3 completed")
            }
        }

        delay(500L)
        job1.invokeOnCompletion {
            println("Job1 is complete")
        }

        println("Job1 Will Be Canceled")
        job1.cancel()

    }

}