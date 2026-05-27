# 04 - CS61B 数据结构回顾

> 结合你在 skeleton-sp24 的项目经历

## 1. Deque（双端队列）

你 proj1a/b/c 的核心数据结构。支持两端添加/删除。

**操作复杂度：**
| 操作 | LinkedListDeque | ArrayDeque |
|------|:-:|:-:|
| addFirst | O(1) | O(1)* |
| addLast | O(1) | O(1)* |
| removeFirst | O(1) | O(1) |
| removeLast | O(1) | O(1) |
| get(i) | O(n) | O(1) |

**LinkedList 实现 — 哨兵节点技巧：**
```java
// 你的代码精髓
public LinkedListDeque61B() {
    sentinel = new Node(null, null, null);
    sentinel.next = sentinel;  // 自己指向自己 → 空队列
    sentinel.prev = sentinel;
    size = 0;
}
```

哨兵节点好处：
- 不需要处理 null 特殊情况
- 头尾操作代码统一
- `isEmpty() → sentinel.next == sentinel` 一行搞定

**Array 实现 — 循环数组：**
```
[_, _, A, B, C, _, _]
       ↑     ↑
     front  back
```
关键公式（CS61B 经典面试题）：
```java
int addIndex = (nextFirst + 1) % items.length;  // front 前移
int lastIndex = (nextLast - 1 + items.length) % items.length;  // back 后移
```

## 2. 链表

你的 `LinkedListDeque61B` 是**双向链表**：

```
sentinel ↔ A ↔ B ↔ C ↔ sentinel
```

| 类型 | 特点 |
|------|------|
| 单向链表 | Node → next，只能单向遍历 |
| 双向链表 | Node ↔ next/prev，可双向遍历（你的实现） |
| 循环链表 | 尾指向头（哨兵方案天然是循环的） |

## 3. 栈 (Stack) 和 队列 (Queue)

Deque 可以实现两者：

```java
// 用 Deque 作栈 (LIFO)
deque.addFirst(x);   // push
deque.removeFirst(); // pop

// 用 Deque 作队列 (FIFO)
deque.addLast(x);    // enqueue
deque.removeFirst(); // dequeue
```

## 4. 树 (Trees) — lab 涉及

**二叉树：**
```java
class TreeNode<T> {
    T item;
    TreeNode<T> left;
    TreeNode<T> right;
}
```

**二叉搜索树 (BST) 性质：**
- 左子树所有值 < 节点值
- 右子树所有值 > 节点值
- 查找 / 插入：平均 O(log n)，最坏 O(n)

**树的遍历 — 必须背熟：**
```
     A
   /   \
  B     C
 / \   / \
D   E F   G

前序 (Preorder):  A B D E C F G  (根→左→右)
中序 (Inorder):   D B E A F C G  (左→根→右)
后序 (Postorder): D E B F G C A  (左→右→根)
层序 (Level):     A B C D E F G  (BFS)
```

**递归的优雅：**
```java
// 你的 getRecursive 就是树递归的缩影
private T getRecursiveHelper(Node current, int index) {
    if (index == 0) {          // base case
        return current.item;
    }
    return getRecursiveHelper(current.next, index - 1);  // 缩小问题
}
```

## 5. 优先队列 / 堆 (Priority Queue / Heap)

CS61B lab 涉及，二叉最小堆性质：
- `parent ≤ children`
- 完全二叉树（用数组存储）

```java
// 数组表示堆
// leftChild(i) = 2*i + 1
// rightChild(i) = 2*i + 2
// parent(i) = (i-1) / 2
```

操作复杂度：
- insert: O(log n)
- removeMin: O(log n)
- peek: O(1)

## 6. 哈希表 (Hash Table)

**核心思想：** key → hash function → index → bucket

冲突解决：
1. **链地址法 (Chaining)**：每个桶是一个链表
2. **开放寻址 (Open Addressing)**：线性探测/二次探测

**负载因子 (Load Factor)：** `loadFactor = size / capacity`
- Java HashMap 默认负载因子 0.75，超过时扩容（rehashing）

## 7. 图 (Graphs) — proj2/proj3 涉及

**表示方式：**
```java
// 邻接表
Map<Integer, List<Integer>> graph = new HashMap<>();

// 邻接矩阵
boolean[][] adj = new boolean[n][n];
```

**遍历：**
- DFS（深度优先）：栈 / 递归
- BFS（广度优先）：队列

## 8. 排序算法

| 算法 | 最好 | 平均 | 最坏 | 空间 | 稳定 |
|------|:----:|:----:|:----:|:----:|:----:|
| 插入 | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| 选择 | O(n²) | O(n²) | O(n²) | O(1) | ❌ |
| 归并 | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| 快速 | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| 堆排序 | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |

**Java 内置排序：**
- `Arrays.sort()` — 基本类型用快排，对象用 TimSort（归并+插入的混合）
- `Collections.sort()` — 内部也是调 TimSort

---

*上一篇：[[03-interfaces-generics]] | 下一篇：[[05-common-patterns]] — 常见模式与易错点*
