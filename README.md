<h1 align="center">FRUIT SHOP MANAGER</h1>

## 🛍️ **Aplikasi Login Sederhana: Manager & Buyer**
Aplikasi ini adalah program Java berbasis CLI (Command Line Interface) yang memungkinkan pengguna untuk login sebagai Manager atau Buyer dan mengakses menu sesuai perannya. Aplikasi ini menggunakan konsep inheritance dan polimorfisme melalui abstract class User dan implementasi dari class Manager dan Buyer.

## 📁 **Struktur Proyek**
├── MainApp.java        <- Program utama \
├── User.java           <- Abstract class pengguna\
├── Manager.java        <- Subclass untuk Manager\
├── Buyer.java          <- Subclass untuk Buyer\
└── Shop.java           <- Kelas toko yang digunakan di menu pengguna

## 🚀 **Cara Menjalankan**
- Clone atau salin kode ini ke proyek Java Anda

- Pastikan semua file (MainApp.java, User.java, Manager.java, Buyer.java, Shop.java) berada di dalam satu folder/proyek.

- Kompilasi dan jalankan file MainApp.java:\
    -  javac MainApp.java 
    -  java MainApp

## 👤**Fitur Login**
1. Login sebagai Manager

        - Username: manager
        - Password: admin123

    Setelah berhasil login, pengguna dapat mengakses fitur manajemen toko melalui method showMenu().

2. Login sebagai Buyer
    - Hanya perlu memasukkan nama.

    Sistem otomatis membuat objek Buyer dan langsung memberikan akses ke menu pembelian.

## 📦 **Kelas dan Method**

1. MainApp.java
    - Menyediakan menu utama.

    - Menangani proses login dan autentikasi.

    - Menginisialisasi objek Shop untuk dipakai oleh semua pengguna.

2. Shop.java\
        (Isi tergantung implementasi Anda) Berisi data produk dan fitur manajemen toko.

3. User.java (abstract)
    - Superclass abstrak untuk semua jenis pengguna.

    Method:
    - `showMenu(Shop shop, Scanner sc)`: Harus diimplementasikan oleh subclass.

4. Manager.java
    - Turunan dari User.

    - Khusus login sebagai manajer toko.

    - Akses untuk mengelola produk (fitur disediakan oleh Shop).

5. Buyer.java
    - Turunan dari User.

    - Digunakan untuk melakukan pembelian produk.

## Pembagian Tugas
| No | Nama     | Tugas | 
|:----|:------------|:-----------------------------|
| 1  | Jean patra paeloran | Class `MainApp` |
| 2  | Rifqi alan maulana| Class `Transaction` |
| 3  | Abdul jabbar fansurna | Class `shop` |
| 4  | Ahmad Farel algifhari | Class `buyer`, `user` |
| 5  | Angel catrina sobbu | Class `Fruit`,`manager` |
