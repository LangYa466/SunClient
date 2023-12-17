package cn.langya.sun.modules.impl.client

import cn.langya.sun.event.Event
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.utils.ClientUtils

/**
 * @author LangYa
 * @ClassName Client
 * @date 2023/12/14 21:09
 * @Version 1.0
 */

class Client: Module("客户端",true,Category.Client) {

    @Event
    override fun onUpdate() {
        ClientUtils.loginfo("Test")
    }

}