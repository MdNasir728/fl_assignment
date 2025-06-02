# Assignment: LRU Cache, Custom HashMap, Book Review App

This repository includes solutions to three problems implemented in  **Java**.

---

## 📌 Q1. LRU Cache (Java)

### ✅ Description:
Implements a Least Recently Used (LRU) Cache with the following operations:
- `get(key)`: Returns the value if present, else null.
- `put(key, value)`: Inserts or updates the key-value pair. Evicts least recently used item if capacity is exceeded.

### ✅ Features:
- O(1) time complexity for all operations.
- Combines a hash map and a doubly linked list.

---

## 📌 Q2. Custom HashMap (Java)

### ✅ Description:
A basic implementation of a HashMap without using STL containers like `unordered_map` or `map`.

### ✅ Features:
- Open hashing (separate chaining) for collision resolution.
- Supports:
  - `put(key, value)`
  - `get(key)`
  - `remove(key)`
- Average-case O(1) time.

---

## 📌 Q3. Book Review App (Java)

### ✅ Description:
A simple Android app in **Java** that allows users to browse, view details, and save books offline.

### ✅ Features:
- **MVVM Architecture** (ViewModel, Repository)
- **Book List Screen**: Title, author, thumbnail
- **Book Detail Screen**: Full description, rating, image
- **Favorites**: Saved in local Room database
- **Offline Mode**: View saved books without internet

