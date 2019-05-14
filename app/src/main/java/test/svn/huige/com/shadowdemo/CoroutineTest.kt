package test.svn.huige.com.shadowdemo

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * 所属系统: ShadowDemo-master-master
 * 所属模块:  ${TODO}
 * 功能描述:  ${TODO}
 * 创建时间: 2019/5/14 18:44
 * 维护人: 李主辉
 * Copyright @ Jerrisoft 2019. All rights reserved.
 *┌─────────────────────────────────────────────────────┐
 *│ 此技术信息为本公司机密信息，未经本公司书面同意禁止向第三方披露．│
 *│ 版权所有：杰人软件(深圳)有限公司                          │
 *└─────────────────────────────────────────────────────┘
 */
class CoroutineTest{

    suspend fun main() {
        Log.i("TAG","1")
        val job = GlobalScope.launch {
            Log.i("TAG","2")
        }
        Log.i("TAG","3")
        job.start()
        Log.i("TAG","4")
    }
}