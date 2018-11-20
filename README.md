# Aplikasi Footbal Schedule
## Submission 3

Aplikasi quiz yang bertujuan untuk menampilkan daftar pertandingan terakhir dan pertandingan yang akan datang sekaligus Event Favorit berbasis android dengan bahasa pemrograman Kotlin.

## Persyaratan dari pembuatan aplikasi ini adalah sebagai berikut :

Syarat

Fitur yang harus ada pada aplikasi Anda adalah:

Semua fitur pada aplikasi sebelumnya harus tetap dipertahankan.

Menyimpan jadwal pertandingan ke halaman Favorite.

Menghapus jadwal pertandingan dari halaman Favorite.

Catatan:

Anda bebas berkreasi dalam membuat aplikasi ini. Tampilan dari aplikasi tidak harus sama dengan contoh yang diberikan.

Wajib menggunakan Anko SQLite.

Dalam menyusun tampilan, Anda boleh menggunakan Anko Layout ataupun XML.


Ketentuan dari aplikasi yang Anda serahkan adalah:

Proyek dari aplikasi harus menggunakan Android Studio.

Bahasa pemrograman yang digunakan adalah Kotlin.

Kirim pekerjaan dalam bentuk folder Proyek Android Studio yang telah di-zip/rar. Tim penilai akan mengulas tugas Anda dalam tempo maksimal 3 (tiga) hari kerja (terkecuali Sabtu, Minggu, dan hari libur nasional). 

Cukup satu kali saja mengumpulkan tugas, tidak perlu berkali-kali. Tindakan demikian hanya akan memperlama proses penilaian.

Anda akan mendapat notifikasi hasil pengumpulan Anda via email, atau Anda dapat mengecek submission pada akun Dicoding Anda.

## Hasil dari submission sebelumnya mendapatkan arahan dan saran sebagai berikut : 
    
Untuk menampilkan data pada EventDetailActivity, gunakanlah endpoint 

https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=EVENT_ID

Jadi Anda tidak perlu melemparkan banyak data menggunakan intent.putExtra.

Hapus kode atau menyederhanakan code yang sesuai dengan convention. Anda bisa memanfaatkan fitur Analyze - Code Cleanup.

Biasakan untuk reformat code dan optimized import supaya code lebih rapi dan bersih dari unused import.

Hindari penggunaan double bang operator (!!) saat pengecekan null, karena akan memaksa suatu variable menjadi non-null. Dan jika ternyata variable tersebut bernilai null, maka bisa menyebabkan NPE. Pelajari kembali tentang fitur  Null Safety.  Periksa kembali semua kode Anda dan jangan biarkan satupun double bang operator tersisa. Sebagai contoh pada:

<code>presenter.getTeamBadge(intentData.idHomeTeam!!,intentData.idAwayTeam!!)</code>

Sehingga menjadi:

<code>intentData.idHomeTeam?.let { intentData.idAwayTeam?.let { it1 -> presenter.getTeamBadge(it, it1) } }</code>
 
# Notes :

## Credits :
All PNG Icons licensed by CC 3.0 BY. Author : Papedesign from https://www.flaticon.com/
