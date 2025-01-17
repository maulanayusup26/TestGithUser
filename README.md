
# GitHub Search User

GitHub Search User adalah aplikasi Android yang memungkinkan pengguna untuk mencari pengguna GitHub dengan cepat dan mudah. Dengan aplikasi ini, pengguna dapat menemukan informasi profil GitHub, seperti nama pengguna, avatar, dan detail profil lainnya.

Aplikasi ini menggunakan arsitektur berbasis MVVM (Model-View-ViewModel) untuk memisahkan logika aplikasi dari antarmuka pengguna, memastikan kode yang bersih dan mudah untuk dikembangkan lebih lanjut.


## Installation

Pastikan Anda memiliki hal berikut ini sebelum menginstal aplikasi:

Android Studio (versi terbaru disarankan)

Perangkat Android atau emulator untuk menjalankan aplikasi

Langkah Instalasi

Clone repository ini:

git clone <https://github.com/maulanayusup26/GitHubUserSearch.git>

Buka proyek di Android Studio.

Sinkronkan Gradle untuk memastikan semua dependensi diunduh.

Hubungkan perangkat Android atau jalankan emulator.

Klik tombol “Run” (▶) di Android Studio untuk menjalankan aplikasi.
    
## Fitur utama

Pencarian pengguna GitHub berdasarkan nama pengguna.

Menampilkan informasi profil seperti avatar, nama pengguna, dan URL profil GitHub.

Antarmuka pengguna yang responsif dan ramah pengguna.

BottomSheet untuk menampilkan detail tambahan.
## Library Utama

Retrofit 2: Untuk komunikasi API.

Gson Converter: Untuk parsing data JSON.

OkHttp Logging Interceptor: Untuk debugging request dan response.
## Komponen Arsitektur Android

Lifecycle ViewModel: Untuk pengelolaan data yang berorientasi pada siklus hidup.

LiveData: Untuk reaktivitas UI.
## UI & Utility

Glide: Untuk memuat gambar, termasuk dukungan transformasi.

CircularImageView: Untuk menampilkan gambar profil dalam bentuk lingkaran.
