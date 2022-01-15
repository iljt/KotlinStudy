package com.example.kotlinstudy.kotlinAdvanced.dsl

/**

 * Created  by Administrator on 2021/12/19 18:05

 */
fun main() {
    // DSL  领域特定语言  == 声明式UI（Compose）
    layout {
        button {

        }
        text {

        }
    }

    // TODO AndroidManifest.xml
    manifest {
        pkg("com.example.kt_base") // 包名
        permission("android.permission.INTERNET") // 权限

        // 四大组件
        application {  // this == 第二个中转站 Component { activity service }

            activity(".MainActivity") { // this == 第三个中转站 IntentFilter
                intent_filter { // this == 第四个中转站 Sub

                    category("android.intent.category.LAUNCHER")
                }
            }

            service(".MyService") {
                intent_filter {

                }
            }

            receiver(".MyReceiver") {
                intent_filter {

                }
            }

            provider(".MyReceiver") {
                intent_filter {

                }
            }
        }
    }

}


//  Manifest 最终的成果，是要返回解析的字符串
fun manifest(action: Manifest.() -> Unit):Manifest{
    var manifest=Manifest()
    manifest.action()
    return manifest
}

class Manifest{
    fun pkg(content: String){

    }
    fun permission(content: String){

    }
    fun application(action: Component.() -> Unit):Component{
        var component=Component()
        component.action()
        return component
    }
}

class Component {

    fun activity(content: String,action: IntentFilter.() -> Unit):IntentFilter{
        var intentFilter=IntentFilter()
        intentFilter.action()
        return intentFilter

    }
    fun service(content: String,action: IntentFilter.() -> Unit):IntentFilter{
        var intentFilter=IntentFilter()
        intentFilter.action()
        return intentFilter
    }

    fun receiver(content: String,action: IntentFilter.() -> Unit):IntentFilter{
        var intentFilter=IntentFilter()
        intentFilter.action()
        return intentFilter
    }

    fun provider(content: String,action: IntentFilter.() -> Unit):IntentFilter{
        var intentFilter=IntentFilter()
        intentFilter.action()
        return intentFilter
    }
}


class IntentFilter{
    fun intent_filter(action: Sub.() -> Unit): Sub{
        var sub=Sub()
        sub.action()
        return sub
    }

    class Sub{
        fun action(content: String){

        }

        fun category(content: String){

        }
    }

}



fun layout(lambda:Content.()->Unit){
    var content=Content()
    content.lambda()
}

class Content{
    fun button(action: () -> Unit){

    }

    fun text(action: () -> Unit){

    }

}