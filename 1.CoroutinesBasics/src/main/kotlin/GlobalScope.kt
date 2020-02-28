import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Hello {

    constructor(){
        GlobalScope.launch {
            delay(1000)
            print("world")
        }
        print("Hello, ")
        Thread.sleep(2000)
    }

}


fun main(arr:Array<String>){
//    println("Hello World")
    Hello()
}