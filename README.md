# OperationEditTextLayout
在EditText右侧选择性添加密显按钮和删除按钮
# 效果图
![](https://github.com/ooftf/OperationEditTextLayout/raw/master/demoImage/demo.gif)
# Gradle配置
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    compile 'com.github.ooftf:OperationEditTextLayout:1.0.2'
}
```
# 使用方式
1.当OperationEditTextLayout只是一个EditText节点的时候
```xml
<com.ooftf.operation.OperationEditTextLayout
	xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:delOperationEnabled="true"
        app:maskOperationEnabled="true">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="@dimen/layout_item_height"
            android:hint="请输入密码"
            android:paddingLeft="@dimen/layout_edge_spacing" />
</com.ooftf.operation.OperationEditTextLayout>
```
2.当OperationEditTextLayout在不止只有EditText节点的时候可以使用 editTextId 指定EditText
```xml
<com.ooftf.operation.OperationEditTextLayout
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:delOperationEnabled="true"
        app:editTextId="@+id/pwd"
        app:maskOperationEnabled="true">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <EditText
                android:id="@+id/pwd"
                android:layout_width="match_parent"
                android:layout_height="@dimen/layout_item_height"
                android:hint="请输入密码"
                android:paddingLeft="@dimen/layout_edge_spacing" />
        </LinearLayout>
</com.ooftf.operation.OperationEditTextLayout>
```
# XML属性
|属性名|描述|默认值|
|---|---|---|
|icon_show|显示密码icon id  |参照效果图|
|icon_hide|隐藏密码icon id  |参照效果图|
|icon_del|清除密码icon id  |参照效果图|
|maskOperationEnabled|是否开启密显转换功能  |false|
|delOperationEnabled|是否开启清除功能  |false|
|editTextId|指定EditText  |无|
|oprationPaddingRight|设置图标的右边距  （由于图标本身带有5dp的padding值，所以即使oprationPaddingRight设置为0，视觉上还会有5dp的距离）|0|
