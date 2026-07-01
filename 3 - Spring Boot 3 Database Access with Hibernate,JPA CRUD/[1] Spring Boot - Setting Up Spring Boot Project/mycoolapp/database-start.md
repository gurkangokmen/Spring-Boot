# MySQL Docker Kurulumu

Bu proje `student_tracker` veritabanina baglanir.

Spring Boot ayarlari:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

## 1. MySQL Container Baslat

Docker Desktop acik olmali.

Terminali proje klasorunde ac:

```powershell
cd "C:\CS\java\Spring-Boot\3 - Spring Boot 3 Database Access with Hibernate,JPA CRUD\[1] Spring Boot - Setting Up Spring Boot Project\mycoolapp"
```

MySQL container'i baslat:

```powershell
docker run -d --name mysql-student-tracker -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:8-oracle
```

Container calisiyor mu kontrol et:

```powershell
docker ps --filter name=mysql-student-tracker
```

Eger daha once ayni isimle container olusturulduysa, tekrar baslatmak icin:

```powershell
docker start mysql-student-tracker
```

## 2. MySQL Hazir mi Kontrol Et

```powershell
docker exec -e MYSQL_PWD=root mysql-student-tracker mysql -uroot -e "SELECT VERSION();"
```

Versiyon bilgisi geliyorsa MySQL hazirdir.

## 3. Veritabani ve Kullanici Olustur

Asagidaki komut SQL scriptini container icindeki MySQL'e gonderir:

```powershell
$sql = @'
-- Drop user first if they exist
DROP USER if exists 'springstudent'@'localhost' ;
DROP USER if exists 'springstudent'@'%' ;

-- Now create user with prop privileges
CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';
CREATE USER 'springstudent'@'%' IDENTIFIED BY 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';
GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'%';

CREATE DATABASE  IF NOT EXISTS `student_tracker`;
USE `student_tracker`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
'@

$sql | docker exec -i -e MYSQL_PWD=root mysql-student-tracker mysql -uroot
```

Not: Spring Boot uygulamasi host makineden Docker container icindeki MySQL'e baglandiginda MySQL tarafinda `localhost` olarak gorunmeyebilir. Bu nedenle `springstudent` kullanicisi hem `'localhost'` hem de `'%'` host'u icin olusturulur.

## 4. Kurulumu Dogrula

Kullanici, veritabani ve tabloyu kontrol et:

```powershell
docker exec -e MYSQL_PWD=root mysql-student-tracker mysql -uroot -e "SELECT User, Host FROM mysql.user WHERE User='springstudent'; SHOW DATABASES LIKE 'student_tracker'; SELECT TABLE_NAME FROM information_schema.tables WHERE table_schema='student_tracker';"
```

`student` tablosunun kolonlarini kontrol et:

```powershell
docker exec -e MYSQL_PWD=springstudent mysql-student-tracker mysql -uspringstudent student_tracker -e "DESCRIBE student;"
```

Beklenen tablo yapisi:

```text
id          int          NO   PRI   NULL   auto_increment
first_name  varchar(45)  YES        NULL
last_name   varchar(45)  YES        NULL
email       varchar(45)  YES        NULL
```

## 5. Spring Boot Uygulamasini Calistir

MySQL container calisir durumdayken Spring Boot uygulamasini baslat:

```powershell
.\mvnw.cmd spring-boot:run
```

## Faydali Docker Komutlari

Container'i durdur:

```powershell
docker stop mysql-student-tracker
```

Container'i tekrar baslat:

```powershell
docker start mysql-student-tracker
```

Container loglarini gor:

```powershell
docker logs mysql-student-tracker
```

Container'i silmek gerekirse once durdur, sonra sil:

```powershell
docker stop mysql-student-tracker
docker rm mysql-student-tracker
```
