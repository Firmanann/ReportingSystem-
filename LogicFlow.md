## Create Keluhan 
Siap, bro! Ini alur logika (*logic flow*) dari fitur form keluhan tadi, udah disesuaikan dengan arsitektur *package by domain* lu dari depan sampai belakang:

**1. Aksi User (Trigger di Panel)**

* Mahasiswa memilih kategori dari *dropdown*, mengetik masalahnya, lalu mengklik tombol **"Kirim Keluhan"**.

**2. Validasi & Perakitan (Di dalam UI / Panel)**

* Sistem langsung mengecek: *Teksnya kosong nggak?* Kalau kosong, muncul *pop-up error* dan proses dibatalkan.
* Kalau datanya ada, sistem mulai membungkus data menjadi satu *object* `Keluhan`:
* **NIM:** Diambil diam-diam dari brankas `UserSession`.
* **ID Kategori:** Diambil dari pilihan *dropdown*.
* **Isi Keluhan:** Diambil dari kolom teks.
* **Tanggal:** Di-*generate* otomatis sesuai waktu detik itu juga.
* **Status:** Langsung diset otomatis ke `"Menunggu"`.



**3. Lempar ke Controller (Logic Layer)**

* *Object* `Keluhan` yang udah utuh tadi dilempar ke `KeluhanController.simpanKeluhan()`. Controller bertugas menerima paket ini dari UI.

**4. Eksekusi Database (Repository Layer)**

* Controller meneruskan paket tersebut ke `KeluhanRepository.save()`.
* Di titik inilah *query* `INSERT INTO keluhan...` dieksekusi secara nyata ke dalam MySQL.

**5. Feedback Layar (Kembali ke UI)**

* MySQL merespons berhasil (`return true`).
* Panel menampilkan *pop-up* notifikasi "Keluhan berhasil dikirim!".
* Terakhir, panel langsung menghapus teks yang baru diketik tadi, sehingga form kembali bersih dan siap dipakai lagi.

Simpel dan terstruktur kan? Nah, karena datanya sekarang udah bisa masuk ke *database*, lu mau kita lanjut bikin fitur buat nampilin datanya di **`RiwayatKeluhanPanel`**, atau lu mau *testing* form ini dulu di sistem lu?