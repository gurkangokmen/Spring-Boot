# CommandLineRunner Nedir?

`CommandLineRunner`, Spring Boot uygulamasi basladiktan sonra otomatik olarak calistirilan bir arayuzdur.

Genelde su isler icin kullanilir:

- Uygulama acilirken test kodu calistirmak
- Veritabanina baslangic verisi eklemek
- DAO / Repository metodlarini denemek
- Uygulama ayaga kalktiktan sonra bir defalik is yapmak

Ornek:

```java
@Bean
public CommandLineRunner commandLineRunner(String[] args) {
    return runner -> {
        System.out.println("Hello, World!");
    };
}
```

Burada `commandLineRunner` metodu bir Spring Bean olusturur. Bu bean'in tipi `CommandLineRunner` oldugu icin Spring Boot uygulama baslangicinda bunu otomatik olarak bulur ve calistirir.

## Ne Zaman Calisir?

`CommandLineRunner`, uygulama context'i hazirlandiktan sonra calisir.

Yani once Spring Boot uygulamayi baslatir, sonra bean'leri olusturur, dependency injection islemlerini yapar ve `ApplicationContext` hazir hale gelir. Bundan sonra `CommandLineRunner` calistirilir.

Basit sira:

```text
1. main metodu calisir
2. SpringApplication.run(...) cagrilir
3. Spring Boot ApplicationContext'i olusturur
4. @Component, @Service, @Repository, @Bean gibi bean'ler bulunur
5. Bean'ler olusturulur
6. Dependency injection yapilir
7. ApplicationContext hazir hale gelir
8. CommandLineRunner bean'leri calistirilir
```

Bu yuzden `CommandLineRunner` icinde Spring bean'lerini kullanabiliriz. Cunku o noktada Spring container artik hazirdir.

## ApplicationContext ile Iliskisi

`ApplicationContext`, Spring'in bean container'idir.

Uygulamadaki nesneleri yonetir:

- Hangi bean'ler var?
- Hangi bean hangi dependency'ye ihtiyac duyuyor?
- Hangi sinif ne zaman olusturulmali?
- Hangi bean hangi bean'e inject edilmeli?

`CommandLineRunner`, `ApplicationContext` olusturulmadan once calismaz. Once context hazirlanir, sonra runner calisir.

Bu nedenle su tarz bir kullanim mumkundur:

```java
@Bean
public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
    return runner -> {
        System.out.println(studentDAO);
    };
}
```

Burada `StudentDAO` parametre olarak verilebilir. Cunku Spring once `StudentDAO` bean'ini olusturur, sonra onu `CommandLineRunner` bean'ine inject eder.

## Otomatik Calismayi Tetikleyen Nedir?

Otomatik calismayi tetikleyen ana nokta sudur:

```java
SpringApplication.run(CruddemoApplication.class, args);
```

Bu metod Spring Boot uygulamasini baslatir.

Baslatma sirasinda Spring Boot context'i olusturur. Context hazir olduktan sonra Spring Boot, context icindeki `CommandLineRunner` tipindeki bean'leri arar.

Buldugu runner'larin `run(...)` metodunu otomatik cagirir.

Yani biz elle sunu cagirmayiz:

```java
commandLineRunner.run();
```

Spring Boot bunu bizim icin yapar.

## Neden @Bean Kullaniliyor?

Asagidaki metod normal bir Java metodu gibi gorunur:

```java
@Bean
public CommandLineRunner commandLineRunner(String[] args) {
    return runner -> {
        System.out.println("Hello, World!");
    };
}
```

Ama `@Bean` oldugu icin Spring bu metodun dondugu nesneyi kendi container'ina ekler.

Metodun donus tipi:

```java
CommandLineRunner
```

oldugu icin Spring Boot bu bean'i ozel olarak tanir ve uygulama baslangicindan sonra calistirir.

Metodun adi onemli degildir. Su da calisir:

```java
@Bean
public CommandLineRunner myStartupCode() {
    return runner -> {
        System.out.println("App started");
    };
}
```

Onemli olan metod adi degil, Spring container'a eklenen bean'in tipidir:

```text
CommandLineRunner
```

## Birden Fazla CommandLineRunner Varsa

Bir uygulamada birden fazla `CommandLineRunner` olabilir.

Ornek:

```java
@Bean
public CommandLineRunner firstRunner() {
    return args -> System.out.println("First runner");
}

@Bean
public CommandLineRunner secondRunner() {
    return args -> System.out.println("Second runner");
}
```

Spring Boot ikisini de calistirir.

Calisma sirasini belirlemek istersek `@Order` kullanabiliriz:

```java
@Bean
@Order(1)
public CommandLineRunner firstRunner() {
    return args -> System.out.println("First runner");
}

@Bean
@Order(2)
public CommandLineRunner secondRunner() {
    return args -> System.out.println("Second runner");
}
```

## Kisa Ozet

`CommandLineRunner`, Spring Boot uygulamasi tamamen ayaga kalktiktan sonra calisan baslangic kodudur.

Calisma mantigi:

```text
SpringApplication.run(...)
        |
        v
ApplicationContext olusur
        |
        v
Bean'ler hazirlanir
        |
        v
CommandLineRunner bean'leri bulunur
        |
        v
run(...) metodu otomatik calistirilir
```

Bu yuzden `CommandLineRunner`, uygulama baslangicinda veritabani islemleri veya test kodlari calistirmak icin uygundur.
