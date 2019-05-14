package test.svn.huige.com.shadowdemo

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * 所属系统: ShadowDemo-master-master
 * 所属模块:  ${TODO}
 * 功能描述:  ${TODO}
 * 创建时间: 2019/5/13 10:01
 * 维护人: 李主辉
 * Copyright @ Jerrisoft 2019. All rights reserved.
 *┌─────────────────────────────────────────────────────┐
 *│ 此技术信息为本公司机密信息，未经本公司书面同意禁止向第三方披露．│
 *│ 版权所有：杰人软件(深圳)有限公司                          │
 *└─────────────────────────────────────────────────────┘
 */
@SuppressLint("ParcelCreator")
@Parcelize
class Student(val id: Int, val name: String, val score: Int) : Parcelable {
    constructor(id: Int, name: String) : this(id, name, 0)
    constructor(id: Int) : this(id, "", 0)
}