package test.svn.huige.com.shadowdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CoroutineTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_test)
        val test = CoroutineTest()
        GlobalScope.launch(start = CoroutineStart.DEFAULT) {
            test.main()
        }


    }
    suspend fun test() {
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            //在后台启动一个新的线程并继续（无阻塞）
//            delay(5000L) //  delay 是一个特别的 挂起函数 ，它不会造成线程阻塞，挂起函数只能在协程中使用）
            Log.i("TAG", "World!" + Thread.currentThread().name) // 在延迟后打印输出
        }
        job.start()
        println("Hello,") // 主线程的协程将会继续等待
    }

}
