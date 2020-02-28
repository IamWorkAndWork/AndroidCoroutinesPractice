import kotlinx.coroutines.*

fun main() {


    runBlocking {

//        launch(Dispatchers.Main) {
//            println("Main Dispatcher. Thread : ${Thread.currentThread().name}")
//        }

        launch(Dispatchers.Unconfined) {

            println("Unconfined. Thread : ${Thread.currentThread().name}")
            delay(100L)
            println("Uncondined2. Thread : ${Thread.currentThread().name}")

        }

        launch(Dispatchers.Default) {
            println("Default, Thread : ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO, Thread : ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext, Thread : ${Thread.currentThread().name}")

        }
    }


}

