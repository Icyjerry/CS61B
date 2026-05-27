# 02 - OOP 核心：封装、继承、多态

> 结合你在 skeleton-sp24 里的实际代码讲解

## 1. 类与对象

**类**是蓝图，**对象**是实例。

```java
// 你的 LinkedListDeque61B 的 Node 内部类
private class Node {
    private T item;        // 字段
    private Node next;
    private Node prev;

    public Node(T item, Node next, Node prev) {  // 构造器
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
}
```

关键字：
- `this` — 当前实例的引用。构造器里 `this.item = item` 区分字段和参数。
- `new` — 在堆上分配内存，调用构造器，返回引用。

## 2. 封装 (Encapsulation)

把数据和操作数据的方法打包在一起，隐藏内部实现。

```java
// 你的 LinkedListDeque61B 完美示范了封装
public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;   // 外部完全不知道 sentinel 的存在
    private int size;        // 外部不能直接改

    public void addFirst(T x) { ... }  // 只暴露这些方法
    public T removeFirst() { ... }
    public int size() { return size; }  // getter
}
```

**访问修饰符：**

| 修饰符 | 类内 | 同包 | 子类 | 任何 |
|--------|------|------|------|------|
| `private` | ✅ | | | |
| (default) | ✅ | ✅ | | |
| `protected` | ✅ | ✅ | ✅ | |
| `public` | ✅ | ✅ | ✅ | ✅ |

## 3. 继承 (Inheritance)

用 `extends` 关键字，子类获得父类的所有非 private 成员。

```java
// 你的 MaxArrayDeque61B 继承自 ArrayDeque61B
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        T max = this.get(0);  // 调用继承来的 get()
        for (int i = 1; i < this.size(); i++) {  // 调用继承来的 size()
            if (comparator.compare(this.get(i), max) > 0) {
                max = this.get(i);
            }
        }
        return max;
    }
}
```

关键点：
- Java **单继承**：一个类只能 extends 一个父类
- 子类构造器**必须先调用父类构造器**（默认调用无参构造器，如果父类没有无参构造器则必须手动 `super(...)` ）
- `@Override` 注解：告诉编译器你在重写方法，如果拼错方法名编译器会报错

```java
@Override
public void addFirst(T x) { ... }  // 重写接口/父类方法
```

## 4. 多态 (Polymorphism)

核心：**一个变量可以指向多种实际类型，运行时决定调用哪个方法**。

```java
// 编译时类型 vs 运行时类型
Deque61B<Integer> deque = new LinkedListDeque61B<>();
deque.addFirst(5);  // 编译时只看到 Deque61B 的方法
                     // 运行时实际执行 LinkedListDeque61B 的版本
```

**方法分派（Dynamic Dispatch）：**
Java 使用**动态绑定**——方法调用取决于对象的运行时类型，不是变量声明的类型。

```java
Deque61B<Integer> d1 = new LinkedListDeque61B<>();
Deque61B<Integer> d2 = new ArrayDeque61B<>();
d1.removeFirst();  // 调用 LinkedListDeque61B 的版本
d2.removeFirst();  // 调用 ArrayDeque61B 的版本
```

**重载 vs 重写：**
| | 重载 (Overload) | 重写 (Override) |
|---|---|---|
| 时机 | 编译时决定 | 运行时决定 |
| 发生位置 | 同一个类里 | 父子类之间 |
| 方法签名 | 参数不同 | 完全相同 |
| 返回类型 | 可以不同 | 相同或协变 |
| 访问权限 | 随意 | 不能更严格 |

你代码里的例子：
```java
// 重载：两个 max() 方法参数不同
public T max() { ... }               // 用默认 comparator
public T max(Comparator<T> c) { ... } // 用传入的 comparator
```

## 5. 抽象类 vs 接口

> 你在 CS61B 里主要用的是接口 `Deque61B<T>`。

| | 接口 (interface) | 抽象类 (abstract class) |
|---|---|---|
| 关键字 | `interface` / `implements` | `abstract class` / `extends` |
| 多实现 | 可以实现多个接口 | 只能继承一个 |
| 字段 | 只能有常量 (`public static final`) | 可以有实例变量 |
| 方法 | Java 8+ 可以有 default 方法 | 可以有具体方法 |
| 构造器 | 不能有 | 可以有 |

```java
// 接口：定义"能做什么"
public interface Deque61B<T> {
    void addFirst(T x);
    T removeFirst();
    default boolean isEmpty() { return size() == 0; }  // Java 8 default 方法
}

// 实现接口
public class LinkedListDeque61B<T> implements Deque61B<T> {
    // 必须实现所有抽象方法
}
```

**什么时候用接口？** 定义行为契约（"能做什么"），多种不相关的类可以共享同一行为。
**什么时候用抽象类？** 共享代码实现，有明确的 "is-a" 关系。

## 6. Object 类

所有类的终极父类。重要方法：

```java
public boolean equals(Object obj)    // 默认比较引用 ==，通常需要重写
public int hashCode()                // 如果重写 equals 必须重写 hashCode
public String toString()             // 返回字符串表示
```

> equals/hashCode 契约：如果 a.equals(b) 为 true，则 a.hashCode() == b.hashCode()。

---

*上一篇：[[01-java-fundamentals]] | 下一篇：[[03-interfaces-generics]] — 接口深入 + 泛型*
