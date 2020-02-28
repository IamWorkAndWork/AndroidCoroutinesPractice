import kotlinx.coroutines.*

fun main(){

   runBlocking {
       launch {
           delay(1000L)
           println("Task from runBlocking")
       }

       GlobalScope.launch {
           delay(500L)
           println("Task From GlobalScope")
       }

       coroutineScope {
           launch {
               delay(1500L)
               println("Task From CoroutineScope")
           }
       }
   }

    println("Program execution will now continue")



}