# Aplikasi Footbal Schedule
## Submission 5 - Final Project

Aplikasi quiz yang bertujuan untuk menampilkan semua yang berkaitan mengenai sepak bola

## Hasil dari submission sebelumnya mendapatkan arahan dan saran sebagai berikut : 
    
Skenario Unit test  yang anda berikan sudah berjalan dengan lancar. Mungkin kedepannya perlu diteliti kembali. Best case nya apabila code method suatu presenter yang ingin di testing dikosongkan atau pun dijadikan comment, testing harusnya gagal karena view yang diharapkan bisa dipanggil, tetapi tidak terpanggilkan di presenter. Namun dalam kondisi ini, testing selalu berhasil. Mohon di submission berikutnya bisa diteliti kembali.

Alternatif lain untuk menerapkan instrumentation test tanpa idling resource maupun delay:

UI Test sebaiknya terisolasi dalam black box yang bebas dari faktor eksternal dan tidak bisa dikontrol seperti internet.

Karena test Anda sudah pasti gagal jika tidak ada koneksi internet, atau bahkan response dari server mengembalikan data yang berbeda.

Berikut referensi yang dibuat oleh salah satu reviewer KADE:https://medium.com/@ekosuhariyadi/dicoding-kade-companion-modul-18c22d269073

Untuk dokumentasi resminya, bisa dibaca disini: https://developer.android.com/training/testing/fundamentals

Biasakan untuk reformat code dan optimized import supaya code lebih rapi dan bersih dari unused import.

Fungsi dan variable   yang hanya diakses dari dalam sebuah class atau file, sebaiknya dijadikan private .

Untuk memasang listener pada viewholder sebaiknya dilakukan di onCreateViewHolder, sesuai dengan best practice dari developer recyclerviewhttps://www.youtube.com/watch?v=KhLVD6iiZQs&t=2s

 
# Notes :

Update beberapa library yang digunakan ke versi yang terbaru.

Akan lebih baik jika elevation pada Toolbar dihapus.

Fungsi ataupun variable yang hanya digunakan pada kelas yang sama sebaiknya dijadikan private.
Hindari penggunaan berlebihan fungsi Thread.sleep() saat menerapkan instrumentation tests & sebaiknya gunakan Idling Resources.

## Credits :
All PNG Icons licensed by CC 3.0 BY. Author : Papedesign from https://www.flaticon.com/
