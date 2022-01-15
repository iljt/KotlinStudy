package k03

fun main() {
    // 不加 inline的效果
    // show((Function0)null.INSTANCE);  对象开辟的浪费

    // 加  inline的效果
    // String var2 = "AAAAA";
    // System.out.println(var2);

    show {
        println("AAAAA")
    }

    // 宏替换 文本替换  inline 会做优化，不需要你开辟对象空间
}
inline fun show(lambda: () -> Unit) {
    lambda()
}