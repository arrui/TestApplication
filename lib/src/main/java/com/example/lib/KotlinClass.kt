package com.example.lib

import kotlinx.coroutines.*
import kotlin.random.Random

class KotlinClass {
    fun testGson(){
    }

    fun testCoroutine() {
        GlobalScope.launch {
            delay(400)
            println("tag0")
        }
        runBlocking {
//            repeat(100000) {
//                val job = launch {
//                    delay(Random(System.currentTimeMillis()).nextLong(3000) + 1000)
//                    println("tag1:$it")
//                }
//                if (it % 2 == 0 && it > 1000) job.cancel()
//            }

            val job = launch {
                delay(200)
                println("tag1")
            }

            coroutineScope {
                val result = async {
                    delay(500)
                    println("tag2")
                }
                delay(100)
                println("tag3")
            }

            //Unconfined坑货
            withContext(Dispatchers.Default) {
                println("tag4")
            }

//            newSingleThreadContext("single")
//            newFixedThreadPoolContext(2,"fixed")
        }
        println("tag5")
    }
}

