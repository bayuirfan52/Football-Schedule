# Aplikasi Footbal Schedule
## Submission 2

Aplikasi quiz yang bertujuan untuk menampilkan daftar pertandingan terakhir dan pertandingan yang akan datang berbasis android dengan bahasa pemrograman Kotlin. 

## Persyaratan dari pembuatan aplikasi ini adalah sebagai berikut :

### Fitur yang harus ada pada aplikasi Anda adalah:

1. Menampilkan 15 jadwal pertandingan, baik yang terakhir maupun yang mendatang berdasarkan League ID. Anda bebas menentukan League ID yang akan digunakan.

2. Menampilkan detail pertandingan dari jadwal yang dipilih.

3. Di dalam halaman detail pertandingan, wajib menampilkan logo dari masing-masing tim.

### Catatan:

1. Anda bebas berkreasi dalam membuat aplikasi ini. Tampilan dari aplikasi tidak harus sama dengan contoh yang diberikan.

2. Anda bebas menggunakan library dari luar, misalnya Retrofit, Rx, dll.

3. Dalam menyusun tampilan diperbolehkan menggunakan Anko maupun XML
    
## Hasil dari submission ini mendapatkan arahan dan saran sebagai berikut : 
    
Untuk menampilkan data pada EventDetailActivity, gunakanlah endpoint 

https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=EVENT_ID

Jadi Anda tidak perlu melemparkan banyak data menggunakan intent.putExtra.

Hapus kode atau menyederhanakan code yang sesuai dengan convention. Anda bisa memanfaatkan fitur Analyze - Code Cleanup.

Biasakan untuk reformat code dan optimized import supaya code lebih rapi dan bersih dari unused import.

Hindari penggunaan double bang operator (!!) saat pengecekan null, karena akan memaksa suatu variable menjadi non-null. Dan jika ternyata variable tersebut bernilai null, maka bisa menyebabkan NPE. Pelajari kembali tentang fitur  Null Safety.  Periksa kembali semua kode Anda dan jangan biarkan satupun double bang operator tersisa. Sebagai contoh pada:

<code>presenter.getTeamBadge(intentData.idHomeTeam!!,intentData.idAwayTeam!!)</code>

Sehingga menjadi:

<code>intentData.idHomeTeam?.let { intentData.idAwayTeam?.let { it1 -> presenter.getTeamBadge(it, it1) } }</code>
 
## Notes :
    
Gunakan repositori ini sebagai bahan referensi dan pembelajaran kedepan. Akan rugi sendiri ketika tidak memahami alur kerja kode yang dibuat.
    
### Selamat ngoding!!! 
