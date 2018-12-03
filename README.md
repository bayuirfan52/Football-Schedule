# Aplikasi Footbal Schedule
## Submission 4

Aplikasi quiz yang bertujuan untuk menampilkan daftar pertandingan terakhir dan pertandingan yang akan datang sekaligus Event Favorit berbasis android dengan bahasa pemrograman Kotlin.

## Persyaratan dari pembuatan aplikasi ini adalah sebagai berikut :

Syarat

Pertahankan semua fitur pada aplikasi sebelumnya.

Menerapkan unit tests pada beberapa fungsi, misalnya fungsi untuk request data ke server.

Menerapkan instrumentation tests dengan skenario yang Anda buat sendiri sesuai behaviour pada aplikasi.

Catatan:

Anda bebas menggunakan library apapun dalam menerapkan pengujian.

Tuliskan skenario pengujian Anda (unit tests & instrumentation tests) pada kolom komentar ketika Anda ingin mengumpulkan tugas ini.

## Hasil dari submission sebelumnya mendapatkan arahan dan saran sebagai berikut : 
    
Update library <code>Glide</code> yang digunakan ke versi yang terbaru.

Hapuslah resource yang tidak pernah digunakan. Anda bisa menggunakan menu  *Refactor - Remove Unused Resources*

Fungsi dan variable yang hanya diakses sekali dalam sebuah file, sebaiknya dijadikan private.

Hapus variable, fungsi, kelas atau kode lainnya yang tidak pernah digunakan, seperti  data class <code>LeagueResponse</code> dan <code>League</code>.

Silahkan perbaiki layout anda karena sepertinya match terakhir tertutup oleh bottom navigation.
 
# Notes :

Skenario Unit test  yang anda berikan sudah berjalan dengan lancar. Mungkin kedepannya perlu diteliti kembali. Best case nya apabila code method suatu presenter yang ingin di testing dikosongkan atau pun dijadikan comment, testing harusnya gagal karena view yang diharapkan bisa dipanggil, tetapi tidak terpanggilkan di presenter. Namun dalam kondisi ini, testing selalu berhasil. Mohon di submission berikutnya bisa diteliti kembali.

Alternatif lain untuk menerapkan instrumentation test tanpa idling resource maupun delay:

UI Test sebaiknya terisolasi dalam black box yang bebas dari faktor eksternal dan tidak bisa dikontrol seperti internet.

Karena test Anda sudah pasti gagal jika tidak ada koneksi internet, atau bahkan response dari server mengembalikan data yang berbeda.

Berikut referensi yang dibuat oleh salah satu reviewer KADE:https://medium.com/@ekosuhariyadi/dicoding-kade-companion-modul-18c22d269073

Untuk dokumentasi resminya, bisa dibaca disini: https://developer.android.com/training/testing/fundamentals

Biasakan untuk reformat code dan optimized import supaya code lebih rapi dan bersih dari unused import.

Fungsi dan variable   yang hanya diakses dari dalam sebuah class atau file, sebaiknya dijadikan private .

Untuk memasang listener pada viewholder sebaiknya dilakukan di onCreateViewHolder, sesuai dengan best practice dari developer recyclerviewhttps://www.youtube.com/watch?v=KhLVD6iiZQs&t=2s

## Credits :
All PNG Icons licensed by CC 3.0 BY. Author : Papedesign from https://www.flaticon.com/
