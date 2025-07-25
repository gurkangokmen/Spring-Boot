
# Kill port


🛠️ 1. Hangi işlem port 8080’i kullanıyor?

PowerShell veya CMD’yi Yönetici olarak çalıştır ve şu komutu gir:


```bash
netstat -aon | findstr :8080
```

Bu komut sana şuna benzer bir çıktı verir:

```bash
TCP    0.0.0.0:8080     0.0.0.0:0     LISTENING     1234
```

🔍 2. PID’ye ait işlemi bul (isteğe bağlı):

```bash
tasklist | findstr 1234
```
Bu, PID 1234'ün hangi uygulamaya ait olduğunu gösterir.

❌ 3. İşlemi sonlandır (portu kill et):

```bash
taskkill /PID 1234 /F
```bash

