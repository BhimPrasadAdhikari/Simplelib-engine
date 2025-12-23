# SimpleLib Engine: A Multi-Module Java Backend

### About me
I am a developer who understands core programming paradigms and chose to master Java by building an enterprise-grade system from scratch. Instead of following basic tutorials, I built this project to explore the complexities of the `JVM`, `multi-threading`, and the `Maven Ecosystem`.

---

### Project Overview
**SimpleLib Engine** is a high-performance library management backend. It is designed to handle diverse resource types (Books, Digital Media) and solve real-world problems like concurrent reservation `"Race Condition"` and `data persistence`.

### Architecture and Development process

I followed a 4-phase development roadmap to ensure Deep understanding of Java stack:

#### Phase 1: Domain modeling and OOP
* **Concepts:** `Inheritance`, `Interfaces`, `Abstract Classes`, and `Enums`.
* **Implementation:** Created a hierarchy where `Book` and `DigitalMedia` inherit from an `AbstractResource`. Used an interface `Searchable` to enforce search capabilities across different media types.

#### Phase 2: Modern Logic with Streams and Generics
* **Concepts:** `Java Streams API`, `Optional`, and `Generics`.
* **Implementation:** Build a generic `InventoryManager<T>`. Replaced traditional for-loops with `Stream pipelines` for filtering and searching, ensuring "NullPointer-safe" code using `Optional`.

#### Phase 3: Concurrency and Thread Safety
* **Concepts:** `ExecuterService`, `Synchronization`, and `Race Conditions`.
* **Implementations:** Simulated a high-traffic environment where multiple users reserve the same book simultaneously. Resolved `race condition` using `synchronized` blocks and `thread-safe collections`.

#### Phase 4: Persistence and I/0
* **Concepts:** `Java NIO.2`, `File I/O`, and `Checked Exceptions`.
* **Implementation:** Developed a `StorageService` to persist library data to the local file system.

---

### Tech Stack
* **Language:** Java 25 
* **Build Tool:** Maven (Multi-Module)
* **IDE:** IntelliJ IDEA

### Project Structure
* `core-models`: The domain layer.
* `inventory-service`: The business logic layer.
* `library-app`: The entry point.