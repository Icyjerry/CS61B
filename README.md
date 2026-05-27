# CS61B - Data Structures (Spring 2024) | UC Berkeley

UC Berkeley CS61B: Data Structures & Algorithms — Spring 2024 课程代码与学习笔记。

This repository contains my solutions to CS61B Spring 2024 projects, labs, and homework, along with comprehensive review notes and LeetCode practice records.

---

## 目录 / Table of Contents

- [课程简介 / About CS61B](#课程简介--about-cs61b)
- [项目概览 / Projects Overview](#项目概览--projects-overview)
- [实验与作业 / Labs & Homework](#实验与作业--labs--homework)
- [复习笔记 / Review Notes](#复习笔记--review-notes)
- [LeetCode 刷题 / LeetCode Practice](#leetcode-刷题--leetcode-practice)

---

## 课程简介 / About CS61B

CS61B 是 UC Berkeley 的「数据结构与算法」课程，由 Josh Hug 教授主讲。课程涵盖 Java 编程基础、面向对象编程、接口与泛型、基本与高级数据结构（链表、树、哈希表、图）、以及算法设计与分析。

CS61B is UC Berkeley's Data Structures & Algorithms course taught by Prof. Josh Hug. Topics include Java fundamentals, OOP, interfaces & generics, fundamental and advanced data structures (linked lists, trees, hash tables, graphs), and algorithm design & analysis.

---

## 项目概览 / Projects Overview

| 项目 | 内容 | 核心知识点 |
|------|------|-----------|
| **proj0** | 2048 Game | Java basics, 2D arrays, control flow |
| **proj1a** | LinkedListDeque61B | Doubly linked list, sentinel node pattern, generics |
| **proj1b** | ArrayDeque61B | Circular array, amortized analysis, resize strategy |
| **proj1c** | GuitarHero + MaxArrayDeque | Comparator, Karplus-Strong algorithm, audio synthesis |
| **proj2a** | Ngordnet (WordNet) | Graph construction, HashMap, file I/O |
| **proj2b** | Ngordnet Extended | Graph traversal algorithms, hyponyms |
| **proj2c** | Ngordnet Complete | Full-stack integration, data structures synthesis |
| **proj3** | BYOW (Build Your Own World) | 2D world generation, graph/algorithm integration, interactive game |

---

## 实验与作业 / Labs & Homework

| 编号 | 内容 / Content |
|------|---------------|
| **lab01** | Java, IntelliJ setup, basic debugging |
| **lab02** | Unit testing with JUnit, TDD |
| **lab03** | Timing tests, algorithmic complexity |
| **lab04** | Git, debugging, peer review |
| **lab05** | Iterators, enhanced for loop |
| **lab06** | Union Find / Disjoint Sets |
| **lab07** | Tree traversals, BST |
| **lab08** | HashMap, HashSet |
| **lab09** | Priority Queue, heap |
| **lab10** | Sorting algorithms |
| **hw0b** | Creative Java exercise |
| **hw2** | Percolation simulation (Union Find) |

---

## 复习笔记 / Review Notes

`cs61b-review/` 目录包含 6 份系统复习笔记，结合本仓库实际代码总结核心知识点：

The `cs61b-review/` folder contains 6 comprehensive review notes covering key CS61B concepts with references to actual code in this repository:

| 文件 | 内容 / Content |
|------|---------------|
| `01-java-fundamentals.md` | 基本类型 vs 引用类型、自动装箱拆箱、静态 vs 实例、值传递、== vs .equals() |
| `02-oop-core.md` | 封装继承多态、抽象类与接口区别、super/this、内部类、实际代码案例 |
| `03-interfaces-generics.md` | 接口深入（Comparable/Comparator/Iterable）、泛型类型擦除、类型界限、通配符 |
| `04-data-structures.md` | Deque 实现对比、链表 vs 数组、栈与队列、递归技巧、哨兵节点 |
| `05-common-patterns.md` | 防御性拷贝、不变性、StringBuilder、迭代器模式、面试速记表 |
| `leetcode-solved.md` | LeetCode 刷题记录（104 题），按难度和主题分类 |

---

## LeetCode 刷题 / LeetCode Practice

累计完成 **104 题**，通过率 53.63%，提交次数 248，累计提交天数 35。

- 简单 (Easy): 69 题 (66.3%)
- 中等 (Medium): 32 题 (30.8%)
- 困难 (Hard): 3 题 (2.9%)

主题分布：数组/字符串、链表、树、动态规划、数学、哈希表、双指针、二分查找等。

详见 `cs61b-review/leetcode-solved.md`

---

## 仓库结构 / Repository Structure

```
skeleton-sp24/
├── proj0/          # 2048 Game
├── proj1a/         # LinkedListDeque — 双向链表 + 哨兵节点
├── proj1b/         # ArrayDeque — 循环数组
├── proj1c/         # GuitarHero — 音乐合成 + MaxArrayDeque
├── proj2a/         # Ngordnet — 图 + HashMap
├── proj2b/         # 图算法
├── proj2c/         # 综合
├── proj3/          # BYOW — 世界生成
├── lab01-lab10/    # 专项练习
├── hw0b, hw2/      # 作业
├── lib/            # 依赖库
└── cs61b-review/   # 复习笔记 + LeetCode 记录
```

---

## 参考 / References

- [CS61B Spring 2024 课程官网](https://sp24.datastructur.es/)
- [Josh Hug's CS61B Materials](https://fa24.datastructur.es/)
