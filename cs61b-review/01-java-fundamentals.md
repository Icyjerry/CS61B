# 01 - Java 基础复习

## 1. 基本类型 vs 引用类型

Java 有 8 种基本类型（primitive types）：

| 类型 | 大小 | 范围 | 默认值 |
|------|------|------|--------|
| `byte` | 1 字节 | -128 ~ 127 | 0 |
| `short` | 2 字节 | -32768 ~ 32767 | 0 |
| `int` | 4 字节 | -2^31 ~ 2^31-1 | 0 |
| `long` | 8 字节 | -2^63 ~ 2^63-1 | 0L |
| `float` | 4 字节 | IEEE 754 单精度 | 0.0f |
| `double` | 8 字节 | IEEE 754 双精度 | 0.0d |
| `char` | 2 字节 | 0 ~ 65535 (Unicode) | '\u0000' |
| `boolean` | ~ | true / false | false |

> 你在 CS61B 里 `size` 计数器就是 `int`，哨兵节点的 `item = null` 利用了引用类型可以为 null 的特点。

**关键区别：**
- 基本类型存的是**值本身**
- 引用类型存的是**指向对象的指针**（类似 C 的指针）

```java
int a = 3;
int b = a;      // b = 3, 独立副本
b = 5;
// a 还是 3

Node n1 = new Node(x);
Node n2 = n1;   // n2 指向同一个对象！
n2.item = y;
// n1.item 也变成了 y！
```

## 2. Java 是 Pass-By-Value（传值）

这是面试高频考点。

```java
public void addFirst(T x) {
    Node newNode = new Node(x, sentinel.next, sentinel);
    // x 是引用类型的拷贝
    // 但你并没有修改 x 指向的对象，只是读取了它
}
```

- 基本类型：拷贝值，函数内修改不影响外部
- 引用类型：拷贝**引用的值**（即地址），函数内通过这个引用可以修改对象内容
- 但让引用本身指向新对象，不影响外部

```java
void change(int[] arr) {
    arr[0] = 99;      // ✅ 修改了外部的数组内容
    arr = new int[5];  // ❌ 不影响外部，只是让局部变量指向新数组
}
```

## 3. `==` vs `.equals()`

```java
String s1 = new String("cs61b");
String s2 = new String("cs61b");

s1 == s2        // false — 比较的是引用地址
s1.equals(s2)   // true  — 比较的是内容
```

> 在你写的 `isEmpty()` 里：`sentinel.next == sentinel` — 这里用 `==` 是对的，因为你要判断是不是**同一个节点对象**。

## 4. 数组

```java
int[] arr = new int[5];          // 默认值全是 0
Deque61B<String>[] arr2 = new Deque61B[10];  // 默认值全是 null

arr.length  // 属性，不是方法！没有括号
```

> 你的 `ArrayDeque61B` 就用到了数组，内部维护一个 `T[] items`。

**数组 vs ArrayList：**
| | 数组 `T[]` | `ArrayList<T>` |
|---|---|---|
| 大小 | 固定 | 动态增长 |
| 泛型 | 不能 `new T[n]`（类型擦除） | 可以直接用 |
| 性能 | 更快 | 有装箱开销（基本类型） |
| 方法 | `.length` | `.size()`, `.add()`, `.get()` |

## 5. 循环与条件

```java
// for — 已知次数
for (int i = 0; i < size; i++) { }

// while — 未知次数
while (current != sentinel) { current = current.next; }

// 增强 for (for-each) — 遍历集合/数组
for (String s : list) { }

// switch — Java 14+ 可用箭头语法
switch (x) {
    case 1 -> doA();
    case 2 -> doB();
    default -> doC();
}
```

## 6. null 的处理

在你的 CS61B 代码里非常常见：

```java
public T get(int index) {
    if (index < 0 || index >= size) {
        return null;   // 哨兵值表示"不存在"
    }
    // ...
}
```

> 防御性编程：对于可能返回 null 的方法，先检查再使用，避免 `NullPointerException`。

---

*下一篇：[[02-oop-core]] — 类、对象、封装、继承、多态*
