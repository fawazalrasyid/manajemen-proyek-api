# Manajemen Proyek API

RESTful API untuk aplikasi manajemen proyek yang dibangun menggunakan Spring Boot, dengan MySQL sebagai database.


## Prerequisites

Ada beberapa hal yang perlu Anda persiapkan untuk menjalankan proyek ini:

- **Java 17** atau versi yang lebih baru
- **Gradle** untuk pengelolaan dependensi dan build
- **MySQL** sebagai database untuk penyimpanan data proyek
- Editor kode pilihan Anda (IntelliJ IDEA, VS Code, dll.)

## Installation

1. Clone proyek ini ke direktori lokal Anda:

   ```bash
   git clone https://github.com/fawazalrasyid/manajemen-proyek-api.git
   ```
   
   ```bash
   cd manajemen-proyek-api
   ```

2. Buat database MySQL dengan nama `manajemen_proyek`:

   ```sql
   CREATE DATABASE manajemen_proyek;
   ```

3. Buka file `src/main/resources/application.properties` dan sesuaikan pengaturan database MySQL Anda:

   ```properties
   spring.application.name=manajemen-proyek-api
   spring.datasource.url=jdbc:mysql://localhost:3306/manajemen_proyek?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

   - **`spring.datasource.url`**: Sesuaikan `host` dan `port` dengan konfigurasi MySQL Anda.
   - **`spring.datasource.username` dan `spring.datasource.password`**: Sesuaikan dengan kredensial MySQL Anda.

4. Build proyek menggunakan Gradle:

   ```bash
   ./gradlew build
   ```

5. Jalankan aplikasi:

   ```bash
   ./gradlew bootRun
   ```

   Aplikasi akan berjalan di `http://localhost:8080`.

## API Documentation

### Endpoint

- **POST** `/api/lokasi`: Menambahkan proyek baru beserta lokasinya.

- **GET** `/api/lokasi`: Mendapatkan daftar semua lokasi.
- **PUT** `/api/lokasi/{id}`: Memperbarui lokasi berdasarkan `id`.

- **DELETE** `/api/lokasi/{id}`: Menghapus lokasi berdasarkan `id`.

- **POST** `/api/proyek`: Menambahkan proyek baru beserta lokasinya.

- **GET** `/api/proyek`: Mendapatkan daftar semua proyek beserta lokasinya.

- **PUT** `/api/proyek/{id}`: Memperbarui proyek berdasarkan `id`.

- **DELETE** `/api/proyek/{id}`: Menghapus proyek berdasarkan `id`.

### Testing
Anda dapat menggunakan collection Postman berikut untuk menguji API

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/11052541-04a96441-590d-437c-b2c6-9c0dfdc1dd87?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D11052541-04a96441-590d-437c-b2c6-9c0dfdc1dd87%26entityType%3Dcollection%26workspaceId%3D89ef917b-ce40-43cf-b60a-2eb2bdb319dc)

## Authors
**Fawaz Al Rasyid**
