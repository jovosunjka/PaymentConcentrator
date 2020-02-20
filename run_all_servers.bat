@echo off

:: "%CD%" vraca putanju do trenutnog direktorijuma (current directory)

::start "EurekaServer" java -jar %CD%\EurekaServer\target\EurekaServer-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\EurekaServer\target\classes\stores\keystore.p12
::echo WAIT FOR THE EURKEA SERVER TO FINISH RUNNING...
::pause
::echo EurekaServer is running!

::start "ProxyServer" java -jar %CD%\ProxyServer\target\ProxyServer-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\ProxyServer\target\classes\stores\keystore.p12
::echo ProxyServer is running!

::start "PaymentMicroservice" java -jar %CD%\payment_microservice\PaymentMicroservice\target\PaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\payment_microservice\PaymentMicroservice\target\classes\stores\keystore.p12
::echo PaymentMicroservice is running!

::start "PayPalMicroservice" java -jar %CD%\pay_pal_microservice\PayPalMicroservice\target\PayPalMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\pay_pal_microservice\PayPalMicroservice\target\classes\stores\keystore.p12 
::echo PayPalMicroservice is running!

:: start "BitcoinMicroservice" java -jar %CD%\bitcoin_microservice\BitcoinMicroservice\target\BitcoinMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\bitcoin_microservice\BitcoinMicroservice\target\classes\stores\keystore.p12

:: echo BitcoinMicroservice is running!

::start "CardPaymentMicroservice" java -jar %CD%\card_payment_microservice\CardPaymentMicroservice\target\CardPaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\card_payment_microservice\CardPaymentMicroservice\target\classes\stores\keystore.p12

::echo CardPaymentMicroservice is running!

 ::start "PCC" java -jar %CD%\PCC\target\PCC-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\PCC\target\classes\stores\keystore.p12

 ::echo PCC is running!

 ::start "Bank1" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.p12 --bin=12345 --server.port=8080 --bank-name=Banka_intesa

 ::echo Bank1 is running!

 ::start "Bank2" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.p12 --bin=13549 --server.port=8082 --bank-name=Vojvodjanska_banka

:: echo Bank2 is running!

::start "Bank3" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.p12 --bin=17951 --server.port=8087 --bank-name=Unicredit_banka

::echo Bank3 is running!

::start "ScienceCenter" java -jar %CD%\..\..\ScienceCenter-master\ScienceCenter\target\ScienceCenter-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\..\ScienceCenter-master\ScienceCenter\target\classes\stores\keystore.p12 --scientific-papers=file:\%CD%\..\ScienceCenter-master\ScienceCenter\target\classes\scientific_papers
::echo ScienceCenter is running!

pause