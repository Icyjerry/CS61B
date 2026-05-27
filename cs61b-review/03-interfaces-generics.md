# 03 - 接口深入 + 泛型

> CS61B 的重头戏，你的 proj1a/1b/1c 大量使用

## 1. 接口作为类型

接口不仅定义契约，还是一种**类型**。这是多态的基础。

```java
// 来自你的 Deque61B.java
public interface Deque61B<T> {
    void addFirst(T x);
    void addLast(T x);
    List<T> toList();
    boolean isEmpty();
    int size();
    T removeFirst();
    T removeLast();
    T get(int index);
    T getRecursive(int index);
}

// 使用接口类型声明变量
Deque61B<String> deque = new LinkedListDeque61B<>();
//    ↑ 编译时类型                ↑ 运行时类型
```

这样写的好处：
- **解耦**：调用方只依赖接口，不依赖具体实现
- **可替换**：随时换 `ArrayDeque61B` 不需要改其他代码
- **可测试**：mock 接口即可

## 2. 泛型基础

> 你的代码里 `<T>` 到处都是！

**为什么需要泛型？**
```java
// 没有泛型时（Java 1.4 以前）
List list = new ArrayList();
list.add("hello");
String s = (String) list.get(0);  // 强制转型，容易 ClassCastException

// 有了泛型
List<String> list = new ArrayList<>();
list.add("hello");
String s = list.get(0);  // 不需要转型，类型安全
```

**语法速查：**
```java
// 泛型类
public class Box<T> {
    private T item;
    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

// 泛型方法
public static <T> T getFirst(List<T> list) {
    return list.get(0);
}

// 多个类型参数
public class Pair<K, V> {
    private K key;
    private V value;
}
```

## 3. 类型擦除

Java 泛型通过**类型擦除**实现：编译器检查类型安全后，擦除所有泛型信息。

```java
// 你写的代码
List<String> list = new ArrayList<>();

// 编译后变成
List list = new ArrayList();  // 原始类型
```

这意味着：
- 运行时 `<String>` 信息不存在
- **不能 `new T[]`** — 这也是为什么 ArrayDeque61B 内部用 `T[]` 需要技巧
- **不能 `instanceof T`**
- 基本类型不能作为类型参数：`List<int>` ❌ → `List<Integer>` ✅

## 4. 边界与通配符

```java
// 上界：T 必须是 Comparable 的子类型
public class SortedBox<T extends Comparable<T>> {
    // T 现在有 compareTo 方法可用
}

// 通配符 — 方法参数
void printAll(List<?> list) {          // 接受任何类型的 List
    for (Object o : list) { ... }
}

void addNumbers(List<? super Integer> list) {  // Integer 或其父类型
    list.add(42);
}

void process(List<? extends Number> list) {     // Number 或其子类型
    Number n = list.get(0);  // 可以读
    // list.add(3.14);       // ❌ 不能写（除了 null）
}
```

**PECS 法则（Effective Java）：**
- **P**roducer `extends` — 只读，用 `? extends T`
- **C**onsumer `super` — 只写，用 `? super T`

## 5. Comparator 与 Comparable

你的 `MaxArrayDeque61B` 同时涉及两者：

**Comparable — 自然排序：**
```java
public class Student implements Comparable<Student> {
    private int id;
    public int compareTo(Student other) {
        return this.id - other.id;  // 负数=小于，0=等于，正数=大于
    }
}
```

**Comparator — 外部排序：**
```java
// 你代码里就是这样用的
Comparator<String> byLength = new Comparator<String>() {
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
};

// Lambda 写法（更简洁）
Comparator<String> byLength = (a, b) -> a.length() - b.length();
```

你的 MaxArrayDeque61B 构造器接收 `Comparator<T>`：
```java
public MaxArrayDeque61B(Comparator<T> c) {
    this.comparator = c;
}
```

这样就可以用不同的比较策略求最大值，而不修改数据结构本身 — 典型的**策略模式**。

## 6. 内部类

你的 `Node` 类就是内部类的经典用法：

```java
public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node {     // 非静态内部类
        private T item;      // 直接使用外部类的 T
        private Node next;
        private Node prev;
    }
    private Node sentinel;
}
```

| 内部类类型 | 关键字 | 特点 |
|-----------|--------|------|
| 成员内部类 | 无 | 可以访问外部类所有成员，持有外部类引用 |
| 静态嵌套类 | `static` | 不持有外部类引用，相当于独立的类 |
| 局部类 | 在方法内 | 作用域仅限于方法 |
| 匿名类 | `new Xxx() { }` | 一次性使用，如 Comparator |

你的 Node 用非静态内部类是对的，因为它需要访问外部类的类型参数 `T`。

---

*上一篇：[[02-oop-core]] | 下一篇：[[04-data-structures]] — CS61B 数据结构回顾*
