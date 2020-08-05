# Keyboard

## 使用方法
和EditText使用方式一致，会读取EditText的属性，根据设置的**inputType**来弹出不通的键盘

### 默认键盘

#### 代码

``` java
 <com.example.keyboard.KeyboardEditText
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:hint="默认键盘"
        android:paddingLeft="15dp"
        android:paddingRight="15dp" />           
```

#### 效果图

![默认键盘](https://github.com/zhangkang19164/Keyboard/tree/master/images/default.png)
### 纯数字键盘

#### 代码

```java
<com.example.keyboard.KeyboardEditText
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:hint="纯数字键盘"
    android:inputType="number"
    android:paddingLeft="15dp"
    android:paddingRight="15dp" />
```

#### 效果图

![数字键盘](https://github.com/zhangkang19164/Keyboard/tree/master/images/number.png)

### 小数点键盘

#### 代码

```java
<com.example.keyboard.KeyboardEditText
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:hint="小数点键盘"
    android:inputType="numberDecimal"
    android:paddingLeft="15dp"
    android:paddingRight="15dp" />
```

#### 效果图



![小数点键盘](https://github.com/zhangkang19164/Keyboard/tree/master/images/numberDecimal.png)
