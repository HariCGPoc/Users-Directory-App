
# 📱 User Directory App

An Android application built with **Jetpack Compose** that displays a list of users and allows viewing detailed information for each user upon selection.

---

## ✨ Features

- ✅ Modern UI using Jetpack Compose
- ✅ Scrollable list of users with clickable items
- ✅ Detailed user profile view including:
  - Name
  - Username
  - Email
  - Company
  - Address
  - Zip, State, Country
  - Phone
- ✅ Image loading with using AsyncImage
- ✅ Clean architecture and reusable UI components

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **AsyncImage** for image loading
- **Material Design 3**
- **Hilt**
- **Coroutines**
- **Flow**
- **Unit Test** Junit and Mockito
- **Clean Architecture** 
---

## 🚀 Getting Started

### Prerequisites

- Android Studio Koala
- Minimum SDK: 24+
- Kotlin 2.1.0

### Installation

1. Clone the repository:
  
   git clone https://github.com/your-username/user-directory-app.git

2. Open the project in Android Studio.

3. Sync Gradle and run the app on an emulator or device.

---

## 📸 Screenshots

<img width="1080" height="2400" alt="Screenshot_20251016_171023" src="https://github.com/user-attachments/assets/ffbdd8cb-0ce5-4b51-aa02-eb3ed1af25e3" />

<img width="1080" height="2400" alt="Screenshot_20251016_171132" src="https://github.com/user-attachments/assets/059b11f9-f6b3-4ef9-849d-84a22862fabb" />


---

## 📂 Project Structure

├── data
│   ├── source remote        # Retrofit APIs, remote data sources
│   ├── repository           # Implements domain repositories
│  
├── di                       # Dependency Injection (Hilt modules)
│
├── domain
│   ├── model                # Domain models (pure Kotlin classes)
│   ├── repository           # Interfaces for repositories
│   └── usecase              # Business logic (UseCases / Interactors)
│
├── presentation
│   ├── navigation            # Navigation graph and routes
│   ├── screen                # User list and detail screens
│   ├── ui.theme              # Material Design Theme
│   ├── viewmodel             # ViewModels for each screen
│   
├── utils                    # Utility constants
│
└── MainActivity.kt          # Entry point


