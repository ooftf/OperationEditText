# OperationEditTextLayout
在EditText右侧选择性添加密显按钮和删除按钮
## 效果图
![](https://github.com/ooftf/OperationEditTextLayout/raw/master/demoImage/demo.gif)
## Gradle配置
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    compile 'com.github.ooftf:OperationEditTextLayout:1.0.3'
}
```
## 使用方式
* 1.使用OperationEditText
```xml
<com.ooftf.operation.OperationEditText
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:paddingLeft="16dp"
        app:oet_delEnabled="true"
        app:oet_drawableMargin="8dp"
        app:oet_maskEnabled="false" />
```
* 2.当OperationEditTextLayout只是一个EditText节点的时候
```xml
<com.ooftf.operation.OperationEditTextLayout
	xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:oetl_delEnabled="true"
        app:oetl_maskEnabled="true">
        <EditText
            android:layout_width="match_parent"
            android:layout_height="@dimen/layout_item_height"
            android:hint="请输入密码"
            android:paddingLeft="@dimen/layout_edge_spacing" />
</com.ooftf.operation.OperationEditTextLayout>
```
* 3.当OperationEditTextLayout在不止只有EditText节点的时候可以使用 editTextId 指定EditText
```xml
<com.ooftf.operation.OperationEditTextLayout
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:oetl_delEnabled="true"
        app:oetl_editTextId="@+id/pwd"
        app:oetl_maskEnabled="true">
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
## OperationEditTextLayout XML属性
|属性名|描述|默认值|
|---|---|---|
|oetl_drawableShow|显示密码icon id  |参照效果图|
|oetl_drawableHide|隐藏密码icon id  |参照效果图|
|oetl_drawableDel|清除密码icon id  |参照效果图|
|oetl_maskEnabled|是否开启密显转换功能  |false|
|oetl_delEnabled|是否开启清除功能  |false|
|oetl_editTextId|指定EditText  |无|
|oetl_paddingRight|设置图标的右边距  （由于图标本身带有5dp的padding值，所以即使oprationPaddingRight设置为0，视觉上还会有5dp的距离）|0|
## OperationEditText XML属性
|属性名|描述|默认值|
|---|---|---|
|oet_drawableShow|显示密码icon id  |参照效果图|
|oet_drawableHide|隐藏密码icon id  |参照效果图|
|oet_drawableDel|清除密码icon id  |参照效果图|
|oet_delDrawableSize| 清除图标的大小 |20dp|
|oet_maskDrawableSize|密显图标的大小  |24dp|
|oet_drawableMargin|清除图标和密显转换图标之间的距离|8dp|
|oet_maskEnabled|是否开启密显转换功能  |false|
|oet_delEnabled|是否开启清除功能  |false|
