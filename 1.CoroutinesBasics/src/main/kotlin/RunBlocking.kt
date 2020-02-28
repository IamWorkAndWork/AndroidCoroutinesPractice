import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RunBlocking {
}


fun main(arr:Array<String>){

    runBlocking {
        repeat(100){
            launch {
                print(".")
            }
        }
    }

}