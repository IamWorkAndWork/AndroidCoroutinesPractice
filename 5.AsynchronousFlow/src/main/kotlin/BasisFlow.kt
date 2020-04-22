import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@InternalCoroutinesApi
class BasisFlow {

    init {
        runBlocking {
            println("Receive Prime Number")

            sendPrimes()
                .collect(object : FlowCollector<Number> {
                    override suspend fun emit(value: Number) {
                        println("number way1 = $value")
                    }
                })

            sendPrimes()
                .collect{
                    println("number way1 = $it")
                }

            sendPrimes()
                .collectIndexed { index, value ->
                    println("number way2 : index = $index ; value = $value")
                }

//            val job = sendPrimes()
//                .onEach {
//                    println("launchIn : $it")
//                }
//                .launchIn(this)


            sendPrimes()
                .onStart {
                    println("onStart")
                }
                .catch { error ->
                    println("onCatch : $error")
                }
                .onCompletion { error ->
                    if (error == null) {
                        println("onCompletion success")
                    } else {
                        println("onCompletion error : $error")
                    }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    println("number = " + it)
                }


//
//            sendPrimes()
//                .collectLatest {
//                    println("Collecting $it")
//                    delay(300) // pretend we are processing it for 300 ms
//                    println("Done $it")
//                }

        }
    }

    fun sendPrimes(): Flow<Int> = flow {
        val primeList = listOf<Int>(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
        primeList.forEach {
            delay(200)
            emit(it)
        }
    }
}

@InternalCoroutinesApi
fun main(arr: Array<String>) {
    BasisFlow()
}


