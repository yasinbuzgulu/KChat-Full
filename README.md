# Chat : Full-stack coding exercise


## Presentation

This application is a Whatsapp-like application. It provides realtime conversations with other people.
Mesajlaşma Uygulaması:

Mesajlaşma Uygulaması:

 

Bu dokümanda, projede kullanılan teknolojilerden, projenin geliştirilmesi esnasında karşılaşılan hatalar ve adımlardan bahsedilecektir.

Kullanılan teknolojiler: 

    Docker(docker-compose), 

    Apache Kafka-zookeeper,

    Apache Avro,

    Websocket,

    MongoDB,

    Spring Boot,

    Angular,

İlk olarak kullanılan teknoloji tercihlerinin kısaca içeriği ve avantajından bahsetmek istiyorum.

Docker Compose, kompleks uygulamaların tanımlanmasını ve çalıştırılmasını sağlayan bir Docker aracıdır. Docker Compose ile birlikte birden fazla container tanımını tek bir dosyada yapabilir, tek bir komut ile uygulamanızın ihtiyaç duyduğu tüm gereksinimleri ayağa kaldırarak uygulamayı çalıştırabilirsiniz.

Biz bu projede docker-compose container'ımız içerisinde mongoDB, kafka ve zookeeper' ın tek seferde çalışmasını sağladık. Dosyamızı da proje içerisine implement ederek tek proje üzerinden birçok sistemin entegre çalışması sağlandı.

#Ayrıntılı bilgi için: :info:   https://docker-curriculum.com/   https://docs.docker.com/compose/


***************************************************

#Apache Kafka-Zookeeper:

#Zookeeper: 

Öncelikle dağıtık bir sistem geliştirmeye başladığımızda devasa birçok sorunla karşılaşırız.
 Farklı sunucular üzerinde çalışacak bir sistemi uyum içerisinde çalışacak şekilde koordine edebiliyor olmak gerçekten güçtür. Kafanızda biraz daha  şekillendirebilmek için araba örneğini düşünelim. 

Bir arabanın hareket edebilmesi ve bizi x’ten y’ye doğru götürebilmesi için birden çok parçanın büyük bir koordinasyon, eş zamanlılık, görevlendirme ve görevleri yerine getirmek gibi birçok şeyi mükemmele yakın bir şekilde gerçekleştirilmesi gerekiyor. Bunun için de iyi bir koordinatöre ihtiyacımız olduğu aşikar.

Dağıtık yapılar için bir koordinatör hizmeti sağlayan uygulama zookeeper’dır. Koordinasyonu sağlamak için birçok görevi yerine getirmesi gerekir uyum, işbirliği, ardışık işlemlerin gerçekleşmesini sağlamak bunlardan bazılarıdır.

#Ayrıntılı bilgi için: :info:   https://www.tutorialspoint.com/zookeeper/index.htm

*******************************************************

#Apache Kafka:
 Sadece bir kaynak sistemimiz ve bir hedef sistemimiz olduğunda, bu sistemler arası veri transferi yapmamız gerektiğinde çözüm basittir. Ancak birçok kaynak, birçok hedef sistemimiz olduğunda ve hepsinin birbirleriyle veri transferi yapması gerektiğinde işler gerçekten karmaşık hale gelecek ve herbir sistemin birbiri ile entegrasyonu gerekecektir.

#Apache Kafka ,
bu sorunları aşmak için doğru çözüm olacaktır. Sistemlerin birbirlerine bağımlıklıklarını ortadan kaldırarak, üzerlerindeki yüklerini de düşürür.

    Topic, kullanıcı tanımlı kategori ismidir. Mesajların tutulduğu yerdir. Veritabınındaki tabloya benzer.

Basit bir ifadeyle;  Kafka bir kuyruklama sistemidir. Kafka, ZooKeeper’ı çakışma (crash) durumlarını tespit etmek, topic yapısını keşif, topic’lerin üretimi ve tüketilmesini stabil halde tutmak için kullanır.

#Ayrıntılı bilgi için: :info: https://www.javatpoint.com/apache-kafka

*******************************************************


#Apache Avro;
 kod oluşturma veya proxy nesneleri gerektirmeyen kompakt bir ikili biçimde veri üreten büyük bir veri serileştirme çerçevesidir.

Protokolleri ve veri türlerini tanımlamak için JSON biçimini kullanır ve verileri kompakt bir ikili biçime serileştirir. 

Avro, şema kavramı üzerinde çalışır. Avro verileri okunurken, söz konusu verilerin yazılması sırasında kullanılan şema her zaman mevcuttur. Avro verileri belirli bir dosyada saklandığında, şema daha sonra başka bir program tarafından işlenmek üzere onlarla birlikte saklanır. Dolayısıyla, verileri okuyan bir program başka bir şema bekliyorsa, her iki şema da mevcut olduğundan bu kolayca çözülebilir.

Biz projede apache avro’yu özellikle kompakt - hızlı ikili veri formatında olması, verilerin depolanması için bir kap dosyası olması ve kolay bir entegrasyona sahip olması sebebiyle kullandık.

# Ayrıntılı bilgi için:    https://techvidvan.com/tutorials/apache-avro-tutorial/
 
 *******************************************************
 
#Websocket;
 sürekli açık olan bir TCP bağlantısı üzerinden çift yönlü mesaj gönderebilme işlemini yapabilen bir protokoldür. HTTP protokolüne uygun olmayan real time web uygulamalarımızdaki karmaşık yapının basitleştirilmesini sağlar.

İnternetin karmaşık haberleşme işlemlerini gerçek-zamanlı olarak gerçekleştirebilmesini sağlayan bir sistemdir.

#Ayrıntılı bilgi için:    https://www.raywenderlich.com/13209594-an-introduction-to-websockets
 
 
 *******************************************************
 
# MongoDB: 
 MongoDB 2009 yılında geliştirilmiş açık kaynak kodlu bir NoSQL veritabanıdır.

MongoDB’de her kayıt bir doküman olarak ifade edilir. Ve bu dökümanlar json formatı şeklinde saklanır. Daha önce ilişkisel veritabanlarıyla ilgilenenlerin bildiği table yapısını burada collection, row yapısını document, column yapısını ise field alır.

Günümüzde aktif olarak kullanılan pek çok programlama dili için driver desteği bulunması bakımından bugün NoSQL sistemler içerisinde en çok tercih edilenlerden biridir.

Projede mongoDB kullanılmasının özel bir sebebi bulunmamakta tamamen yeni teknolojiler kullanılırken bir de NoSql bir database kullanmak istedim bu sebeple kullandım. Tam anlaşılması için halen birkaç örnek proje yapmam gerekmekte.

#Ayrıntılı bilgi için: :info:    https://docs.mongodb.com/manual/tutorial/
 
 
 *******************************************************
 
 Spring Boot ve angular framework’leri daha önce birçok projede kullanıldığı ve anlatımları yapıldığı için buraya yalnızca ayrıntılı bilgi linki bırakacağım.

# Ayrıntılı bilgi için: :info:    https://angular.io/tutorial     https://www.javatpoint.com/spring-boot-tutorial
 
 
 *******************************************************
########## Proje adımları:############

 1 Bilgisayarımızda docker-compose’un en güncel hali olamalıdır. 

1.1 :warning: Proje esnasında 1.25 docker-compose kullanırken oluşan hatalar 1.29.2 olarak güncellenince çözülmüştür.

1.2 :warning:  docker-compose.yml dosyası proje içerisinde olmalıdır. Biz projemizde deploy klasörü içerisine koyduk.

1.3 docker-compose.yml dosyasının olduğu klasörde terminal açılarak  aşağıdaki kod[1] yazılır ve ayağa kaldırılır. Daha sonra docker üzerinde çalışır olan sistemleri [2] kod ile listeleyebiliriz.
docker-compose up -d
docker ps

2 Spring boot projemizi  https://start.spring.io/  linkinden oluştururuz.

2.1 Spring Weblux - Spring Kafka -  Websocket - MongoDB dependency’leri eklenir.

 2.2  Bunlara ek projede gördüğünüz avro ve buna bağlı serializer .. dependency’leri de eklenir.

:warning: Burada dikkat etmemiz gereken → https olmasıdır, http olması durumunda hata ile karşılaşılır.
 
 :warning: Bir diğer dikkat edilmesi gereken avro dosyasının resources’a eklenmesi ve Maven’dan clean → install → reload ile yüklenme sağlanmalıdır. 
 
 

 (target → generated-sources → avro ) dan kontrol edilebilir.
 
 3 Artık backend servis kodlamasına başlayabiliriz.

3.1 Şekilde gördüğünüz yapıda olduğu gibi kodlamalar yapıldı.

Kısaca bahsedecek olursak, modellerimiz ihtiyaca göre oluşturuldu, veri depolama için repository’ler oluşturuldu, servis ve controller’lar oluşturuldu ve kafka kullanımına bağlı producer-consumer oluşturuldu.
Konfigürasyonlar yapıldı ve çalıştırıldı.

4 Frontend kodu için angular kullanıldı.

:warning:  Burada dikkat edilmesi gereken angular cli 10.1.7 kullanılmıştır.

4.1 npm install komutu girilerek ile veya package.json açılarak npm install önerisi çıkması sağlandı. (Bu madde hazır dosya açılırken kullanılır)

4.2 Global bir cli yüklemek için [1] aşağıdaki komut çalıştırılır.
npm install -g @angular/cli

4.3 Sonrasında aşağıda gördüğünüz yapıda kodlamalar yapıldı.


 
 
 *******************************************************
 
 
 
 *******************************************************

