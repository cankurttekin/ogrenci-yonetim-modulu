# Öğrenci Yönetim Modülü

Bu proje, öğrencilerin temel bilgilerini kaydetmek, görüntülemek, güncellemek ve silmek için basit bir web uygulamasıdır.

## İçindekiler
1.  [Proje Açıklaması](#proje-açıklaması)
2.  [Kurulum](#kurulum)
3.  [Çalıştırma](#çalıştırma)
4.  [Bağımlılıklar](#bağımlılıklar)
5.  [Yapılandırma Dosyaları](#yapılandırma-dosyaları)
6.  [Lisans](#lisans)

## Proje Açıklaması
Bu proje, Java tabanlı bir web uygulamasıdır ve öğrencilerin bilgilerini (ad, soyad, öğrenci numarası, bölüm ve kayıt tarihi) yönetmek için kullanılmaktadır. Kullanıcılar öğrenci ekleyebilir, mevcut öğrencileri düzenleyebilir, silebilir ve arama yapabilir. Veritabanı olarak PostgreSQL kullanılmaktadır. Arayüz JSF ve PrimeFaces kullanılarak oluşturulmuştur.

## Kurulum
1.  **Java Development Kit (JDK):** Projenin derlenmesi ve çalıştırılması için JDK 8 veya üzeri gereklidir.
2.  **Apache Maven:** Proje bağımlılıklarını yönetmek ve derlemek için Maven gereklidir.
3.  **PostgreSQL:** Veritabanı olarak PostgreSQL 10 veya üzeri gereklidir.
4.  **Web Sunucusu:** Uygulamayı çalıştırmak için Apache Tomcat gibi bir web sunucusu gereklidir.
5.  **Veritabanı Oluşturma:**
    *   PostgreSQL'de `studentdb` adında bir veritabanı oluşturun.
    *   `src/main/resources/database/ddl.sql` dosyasındaki SQL komutlarını kullanarak `students` tablosunu oluşturun.

## Çalıştırma
1.  Repo'yu clone'layin ```git clone https://github.com/cankurttekin/ogrenci-yonetim-modulu.git```
1.  Projenin kök dizinine gelin ```cd ogrenci-yonetim-modulu```
2.  Projeyi derleyin ```mvn clean install```
3.  `target/` dizinindeki `ogrenci-yonetim-modulu-1.0.war` dosyasını web sunucunuzda dağıtın.
    - Konfigürasyonsuz basit bir şekilde Tomcat ile dağıtmak için örnek:
    - ```sudo cp target/ogrenci-yonetim-modulu-1.0.war /var/lib/tomcat/webapps/```
    - ```sudo systemctl start tomcat```
    - localhost:8080/ogrenci-yonetim-modulu-1.0/
5.  Web sunucunuzu başlatın.
6.  Uygulamaya tarayıcınız üzerinden erişebilirsiniz. Varsayılan olarak `student.xhtml` dosyası açılacaktır.

## Bağımlılıklar

Proje aşağıdaki temel bağımlılıklara sahiptir:

*   **javax.faces:javax.faces-api:** JSF (JavaServer Faces) API'si.
*   **org.primefaces:primefaces:** Kullanıcı arayüzleri için PrimeFaces kütüphanesi.
*   **org.hibernate:hibernate-core:** Veritabanı işlemleri için Hibernate ORM.
*   **org.postgresql:postgresql:** PostgreSQL JDBC sürücüsü.
*   **javax.persistence:javax.persistence-api:** JPA (Java Persistence API) API'si.
*   **javax.servlet:javax.servlet-api:** Servlet API'si.
*   **javax.servlet:jstl:** JSTL (JavaServer Pages Standard Tag Library).
*   **javax.enterprise:cdi-api:** CDI (Contexts and Dependency Injection) API'si.
*    **org.jboss.weld.se:weld-se-core:** CDI implementasyonu.

Bu bağımlılıklar `pom.xml` dosyasında belirtilmiştir. Maven, bu bağımlılıkları otomatik olarak indirecektir.


## Yapılandırma Dosyaları

*   **hibernate.cfg.xml:** Bu dosya, Hibernate ORM'nin veritabanına bağlanması için gerekli yapılandırmaları içerir.
    *   `hibernate.connection.driver_class`: PostgreSQL JDBC sürücü sınıfı.
    *   `hibernate.connection.url`: Veritabanı bağlantı adresi.
    *   `hibernate.connection.username`: Veritabanı kullanıcı adı.
    *   `hibernate.connection.password`: Veritabanı şifresi.
    *   `hibernate.dialect`: Kullanılacak SQL dialekti.
    *   `hibernate.show_sql`: SQL sorgularının konsolda gösterilip gösterilmeyeceği.
    *   `hibernate.hbm2ddl.auto`: Şema güncelleme stratejisi (`update` olarak ayarlanmıştır).

## Lisans
GPLv3
