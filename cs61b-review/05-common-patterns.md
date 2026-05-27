# 05 - 常见模式、陷阱与最佳实践

## 1. 防御性拷贝

```java
// ❌ 不好：直接返回内部引用
public List<T> toList() {
    return this.internalList;  // 外部可以修改 deque 内部！
}

// ✅ 你的做法：返回新列表
public List<T> toList() {
    List<T> list = new ArrayList<>();
    Node current = sentinel.next;
    while (current != sentinel) {
        list.add(current.item);
        current = current.next;
    }
    return list;
}
```

## 2. 不变性 (Immutability)

不可变对象一旦创建就不能修改。好处：线程安全、可缓存、可放心共享。

```java
// Java 示例
String s = "hello";
s.toUpperCase();  // 返回新 String，原对象不变！
System.out.println(s);  // 还是 "hello"

// 可变对象
StringBuilder sb = new StringBuilder("hello");
sb.append(" world");  // 原地修改
```

## 3. 异常处理

```java
// 受检异常 (checked)：必须处理
try {
    FileReader f = new FileReader("file.txt");
} catch (FileNotFoundException e) {
    System.out.println("文件不存在");
}

// 非受检异常 (unchecked)：RuntimeException 及其子类，可以不处理
throw new IllegalArgumentException("index out of bounds");
```

## 4. try-with-resources（自动关闭）

```java
// ❌ 老写法
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("file.txt"));
    // read...
} catch (IOException e) {
    // ...
} finally {
    if (br != null) br.close();
}

// ✅ Java 7+
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    // read...
}  // br.close() 自动调用
```

## 5. 常见陷阱

### 5.1 Integer 缓存
```java
Integer a = 127;
Integer b = 127;
a == b   // true — JVM 缓存了 -128~127 的 Integer

Integer c = 128;
Integer d = 128;
c == d   // false — 超出缓存范围！
c.equals(d)  // true — 用 equals！
```

### 5.2 增强 for 中修改集合
```java
List<String> list = new ArrayList<>();
for (String s : list) {
    if (s.isEmpty()) {
        list.remove(s);  // ❌ ConcurrentModificationException!
    }
}

// ✅ 用 Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().isEmpty()) it.remove();
}
```

### 5.3 空指针
```java
// 你的代码里防御得很好：
public T get(int index) {
    if (index < 0 || index >= size) {
        return null;  // 明确处理边界情况
    }
    // ...
}
```

### 5.4 字符串拼接性能
```java
// ❌ 循环中直接用 +（每次都创建新 String）
String s = "";
for (int i = 0; i < 10000; i++) {
    s += i;  // O(n²)
}

// ✅ 用 StringBuilder
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append(i);  // O(n)
}
```

## 6. Iterator 与 Iterable

```java
// 让自定义集合可以用增强 for 循环
public class LinkedListDeque61B<T> implements Deque61B<T>, Iterable<T> {
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private Node current = sentinel.next;
        public boolean hasNext() { return current != sentinel; }
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
```

## 7. Shadowing（变量遮蔽）

```java
public class Example {
    private int size;  // 字段

    public void setSize(int size) {   // 参数也叫 size
        size = size;   // ❌ 把参数赋给自己！字段没变
        this.size = size;  // ✅ 用 this 区分
    }
}
```

你代码里做对了：`this.item = item;` `this.comparator = c;`

## 8. 面试/考试速记

| 概念 | 一句话 |
|------|--------|
| 封装 | 隐藏内部实现，只暴露接口 |
| 继承 | `extends`，获得父类能力 |
| 多态 | 同一个方法名，不同对象不同行为 |
| 抽象 | `interface`/`abstract`，定义契约不定义实现 |
| 泛型 | `<T>`，编译时类型检查 |
| 装箱 | 基本类型 → 包装类（`int → Integer`） |
| 拆箱 | 包装类 → 基本类型（`Integer → int`） |
| 变参 | `void foo(String... args)` |
| 注解 | `@Override` `@Deprecated` `@SuppressWarnings` |
| 枚举 | `enum Color { RED, GREEN, BLUE }` |

---

*上一篇：[[04-data-structures]]*

---

## 附录：项目回顾

```
skeleton-sp24/
├── proj1a/  LinkedListDeque  ← 双向链表 + 哨兵节点
├── proj1b/  ArrayDeque       ← 循环数组
├── proj1c/  GuitarHero       ← 应用：音乐合成 + MaxArrayDeque
├── proj2a/  Ngordnet         ← 图 + HashMap
├── proj2b/                   ← 图算法
├── proj2c/                   ← 综合
├── proj3/   BYOW             ← 世界生成（图/算法综合）
├── lab01-10                   ← 各种专项练习
└── hw0b, hw2                  ← 作业
```
