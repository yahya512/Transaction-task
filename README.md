# Transaction App

An Android transaction management application built with **Kotlin**, following **Clean Architecture** and **MVVM** principles.

The application allows users to browse their transaction history, view detailed information about a specific transaction, and share a screenshot of the transaction details with other applications.

## Features

* Browse requested transaction history
* Paginated transaction list
* View transaction details
* Capture and share transaction details as a screenshot
* Reactive UI state handling using `StateFlow`
* Clean separation between Presentation, Domain, and Data layers

## Screens

### 1. Transaction History

The main screen displays the user's **requested transaction history** in a scrollable list.

The transaction history is implemented using **Pagination**, so transactions are loaded in pages instead of loading the entire history at once. As the user reaches the end of the list, the next page is requested and added to the existing transactions.

This approach keeps the UI responsive and avoids loading a large amount of data at once.

![Transaction History]([screenshots/transaction_history.png](https://github.com/yahya512/Transaction-task/blob/f6fe88923c4edf3d96411be55d746223fec115a5/app/src/main/java/com/example/e_commerceapp/screenshots/Screenshot_20260901_210431.png))

---

### 2. Transaction Details

By selecting a transaction from the history, the user can navigate to the **Transaction Details** screen.

This screen displays the complete information related to the selected transaction in a clear and organized layout.

![Transaction Details]([screenshots/transaction_details.png](https://github.com/yahya512/Transaction-task/blob/f6fe88923c4edf3d96411be55d746223fec115a5/app/src/main/java/com/example/e_commerceapp/screenshots/Screenshot_20260901_210440.png))

---

### 3. Share Transaction

The Transaction Details screen also provides a **Share** action.

When the user presses the share button, the application captures the transaction details as a screenshot and opens the Android share sheet, allowing the user to choose a suitable application to share the image through.

![Share Transaction]( https://github.com/yahya512/Transaction-task/blob/f6fe88923c4edf3d96411be55d746223fec115a5/app/src/main/java/com/example/e_commerceapp/screenshots/Screenshot_20260901_210456.png)

## Architecture

The application follows **Clean Architecture** with an MVVM-based presentation layer.

```text
UI
 ↓
ViewModel
 ↓
UseCase
 ↓
Repository
 ↓
Data Source
```

The architecture separates responsibilities between the different layers, making the application easier to maintain and extend.

The project also follows a feature-oriented structure where the business features are kept clear while the layers remain separated inside the feature.

## Pagination

The transaction history uses **Pagination** to load transactions incrementally.

Instead of requesting the complete transaction history at once, the application requests a specific page and loads additional transactions when the user approaches the end of the list.

```text
Open Transaction History
        ↓
Load Page 1
        ↓
Display Transactions
        ↓
User Scrolls
        ↓
Load Page 2
        ↓
Append New Transactions
        ↓
Continue...
```

The important part is that newly loaded transactions are **added to the existing list rather than replacing it**.

## State Management

The UI state is handled using **StateFlow**, allowing the UI to react to changes such as loading, success, and error states.

`StateFlow` is suitable for representing the current state of a screen, while event-based actions can be handled separately when needed.

## Technologies

* **Kotlin**
* **Android SDK**
* **XML**
* **MVVM**
* **Clean Architecture**
* **Hilt**
* **StateFlow**
* **Coroutines**
* **RecyclerView**
* **Pagination**
* **Git & GitHub**

## Project Structure

```text
Transaction App
│
├── Presentation
│   ├── Transaction List
│   ├── Transaction Details
│   └── ViewModels
│
├── Domain
│   ├── Models
│   ├── Repository Interfaces
│   └── Use Cases
│
├── Data
│   ├── API
│   ├── DTOs
│   └── Repository Implementations
│
└── Core
    └── Shared Components
```

The project structure keeps the application responsibilities separated while making the main business features easy to identify.

## Purpose

This project was built to practice building a real-world Android application using modern architectural principles, reactive state management, dependency injection, and paginated data loading.
