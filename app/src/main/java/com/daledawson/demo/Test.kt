package com.daledawson.demo

import kotlinx.coroutines.*

/**
 * 创 建 人：zhengquan
 * 创建日期：2020/5/20
 * 修改时间：
 * 修改备注：
 */
fun main() {
    GlobalScope.launch(Dispatchers.IO) {
        delay(3000)
        println("协程执行结束")
    }
    println("主线程执行结束")
    Thread.sleep(5000)

    test()
    println("协程执行结束。。。")
}

//fun main(agrs: Array<String>) = runBlocking {
//    val job = async {
//        delay(500)
//        println("async..." + Thread.currentThread().name)
//        return@async "hello"
//    }
//    println(job.await())
//}

fun test() = runBlocking {
    repeat(10) {
        println("协程挂起----$it")
        delay(500)
    }
}