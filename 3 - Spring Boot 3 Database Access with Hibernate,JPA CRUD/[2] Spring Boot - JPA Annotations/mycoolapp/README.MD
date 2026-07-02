# Spring Boot - JPA Annotations

Bu projede `Student` sinifini veritabani tablosu ile eslemek icin JPA annotation'lari kullanilir.

## Kullanilan JPA Annotation'lari

### `@Entity`

Bir Java sinifinin JPA entity oldugunu belirtir.

Entity siniflari veritabanindaki bir tabloyu temsil eder. Bu projede `Student` sinifi bir entity olarak tanimlanmistir.

```java
@Entity
public class Student {
}
```

Bu annotation olmadan JPA, sinifi veritabani tablosu ile eslenecek bir model olarak algilamaz.

### `@Table`

Entity sinifinin hangi veritabani tablosu ile eslenecegini belirtir.

```java
@Table(name = "student")
```

Burada `Student` sinifi veritabanindaki `student` tablosu ile eslenir.

Eger `@Table` kullanilmazsa JPA varsayilan olarak sinif adini tablo adi olarak kullanir.

Ornegin class adi `Student` oldugu icin JPA varsayilan tablo adini `Student` olarak kabul edebilir. Ancak Spring Boot + Hibernate projelerinde varsayilan naming strategy genellikle class adini fiziksel tablo adina cevirirken kucuk harfe donusturur. Bu nedenle `Student` entity sinifi veritabaninda `student` tablosunu arayabilir ve uygulama calisabilir.

Yine de bu davranis framework ayarlarina bagli oldugu icin en garanti ve okunabilir kullanim tablo adini acikca belirtmektir:

```java
@Table(name = "student")
```

Bu sekilde entity sinifinin hangi tablo ile eslestigi net olur ve naming strategy degisse bile tablo eslemesi bozulmaz.

### `@Id`

Entity icindeki primary key alanini belirtir.

```java
@Id
private int id;
```

Her entity sinifinda bir primary key olmalidir. Bu projede `id` alani `student` tablosundaki benzersiz kimlik alanidir.

### `@GeneratedValue`

Primary key degerinin nasil uretilecegini belirtir.

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

`GenerationType.IDENTITY`, id degerinin veritabani tarafindan otomatik artan kolon olarak uretilmesini saglar. MySQL gibi veritabanlarinda `AUTO_INCREMENT` kolonlari icin yaygin kullanilir.

Yaygin stratejiler:

- `GenerationType.IDENTITY`: Id degerini veritabani otomatik uretir.
- `GenerationType.SEQUENCE`: Id degeri veritabani sequence yapisi ile uretilir.
- `GenerationType.TABLE`: Id degeri ayri bir tablo kullanilarak uretilir.
- `GenerationType.AUTO`: Stratejiyi JPA provider otomatik secer.

### `@Column`

Sinif alaninin veritabani tablosundaki hangi kolon ile eslenecegini belirtir.

```java
@Column(name = "first_name")
private String firstName;
```

Bu ornekte Java tarafindaki `firstName` alani, veritabanindaki `first_name` kolonu ile eslenir.

Bu projedeki kolon eslemeleri:

| Java Alani | Veritabani Kolonu |
| --- | --- |
| `id` | `id` |
| `firstName` | `first_name` |
| `lastName` | `last_name` |
| `email` | `email` |

`@Column` icinde sik kullanilan ozellikler:

- `name`: Kolon adini belirtir.
- `nullable`: Kolonun `NULL` deger alip alamayacagini belirtir.
- `unique`: Kolon degerinin benzersiz olup olmayacagini belirtir.
- `length`: String kolonlari icin maksimum uzunlugu belirtir.

Ornek:

```java
@Column(name = "email", nullable = false, unique = true, length = 100)
private String email;
```

## Bu Projedeki Entity Ornegi

```java
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
}
```

Bu yapi sayesinde JPA, `Student` nesnelerini `student` tablosundaki satirlarla eslestirir.
