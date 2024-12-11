# Kredi Başvuru Projesi 

Bu proje, Spring Boot tabanlı mikroservislerden ve React önyüzünden oluşan bir web uygulamasıdır. Uygulama, aşağıdaki özellikleri içermektedir:

- **Feign Client** : Mikroservis iletişimi.
- **RabbitMQ** : Asenkron mikroservis iletişimi.
- **Spring Mail Sender** : E-posta gönderimi.
- **Spring JPA**: Veritabanı işlemleri için ORM desteği.
- **Spring Batch** : Zamanlanmış görevler.
- **Spring AOP** : Çapraz kesişen işlemler.
- **BFF** : Backend for Frontend
- **JUnit** : Unit testing (birim testi) yapmak için kullanılan kütüphanedir.
- **Mockito** : Birim testlerinde mocking (sahte nesne) oluşturmaya olanak tanıyan kütüphanedir.
- **SonarQube** : Kod kalitesini arttırmayı sağlayan araç.
- **React**: Kullanıcı arayüzü için kullanılan JavaScript kütüphanesi.
- **React Router**: Sayfa yönlendirmelerini yönetmek için kullanılan kütüphane.
- **Redux**: Uygulama durumu (state) yönetimi için kullanılan kütüphane.
- **Axios**: API istekleri göndermek için kullanılan kütüphane.

---
## Proje Detayları
**1- Anasayfa** : Başlangıç sayfasıdır. Bu sayfada axios kütüphanesi ile api isteği yapılır. İstek "creditapp-bff" de  karşılanır. Bff katmanı Feign client ile "customer-be" servisinden verileri çekip önyüze aktarır. Geçerli bir id girilmesi durumunda altta çıkan buton ile diğer sayfaya geçilir. Sayfa geçisi için router kütüphanesi, müşteri bilgilerinin global değerlere atanarak diğer sayfada kullanılması için redux kütüphanesi kullanılmıştır.  

![1-Anasayfa.jpg](screenshot%2F1-Anasayfa.jpg)

![2-Anasayfa.jpg](screenshot%2F2-Anasayfa.jpg)

![3-Anasayfa.jpg](screenshot%2F3-Anasayfa.jpg)

**2- Kredi Başvuru Sayfası** : Kredi seçenekleri girildikten sonra bff aracılığı ile "aplication-be" servisine postmapping api isteği iletilir. Veritabanında yeni kayıt açılması durumunda önyüzde başvuru numarası bildirilip mail atılacağı bilgisi verilir. 

![4-Basvuru.jpg](screenshot%2F4-Basvuru.jpg)

![5-Basvuru.jpg](screenshot%2F5-Basvuru.jpg)

**3- Kredi Değerlendirme ve mail gönderimi** : "aplication-be" servisi "evaluation-be" servisine kredi başvuru bilgilerini iletir. "evaluation-be" kendi veritabanındaki müşteri limit bilgisine göre krediye onay veya red verip sonucu "aplication-be" servisine iletir. Bu iki mikroservis RabbitMQ aracılığı ile asenkron haberleşir. "aplication-be" gelen sonuca göre veritabanındaki status bilgisini değiştirip MailSender servisi ile müşteriye mail atar. 

![6-mail.jpg](screenshot%2F6-mail.jpg)

**4- Sprig AOP ile Mail Loglama** : @AfterReturning anatasyonu ile her sendEmail metodu çalışma sonrası atılan mailin bilgileri veritabanına kayıt edilir.  

![7-aop_db.jpg](screenshot%2F7-aop_db.jpg)

**5- Spring Batch ile İstatik** : "mailbatch-be" servisinde her saat başı yazılan görevler çalışır. Son bir saatlik dilimdeki Maillog kayıtlarına bakılarak red ve ok kredi sonuçlarının sayısı hesaplanır. Bu veriler istatistik olarak veritabanına kayıt edilir.  

![8-batch_db.jpg](screenshot%2F8-batch_db.jpg)

