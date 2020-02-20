@echo off

:: "%CD%" vraca putanju do trenutnog direktorijuma (current directory)

start "EurekaServer" java -jar %CD%\EurekaServer\target\EurekaServer-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\EurekaServer\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\EurekaServer\target\classes\stores\truststore.jks
echo WAIT FOR THE EURKEA SERVER TO FINISH RUNNING...
pause
echo EurekaServer is running!

start "ProxyServer" java -jar %CD%\ProxyServer\target\ProxyServer-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\ProxyServer\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\ProxyServer\target\classes\stores\truststore.jks
pause
echo ProxyServer is running!

:: start "PkiMicroservice" java -jar %CD%\pki_microservice\PkiMicroservice\target\PkiMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\pki_microservice\PkiMicroservice\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\pki_microservice\PkiMicroservice\target\classes\stores\truststore.jks
:: pause
:: echo PkiMicroservice is running!

start "PaymentMicroservice" java -jar %CD%\payment_microservice\PaymentMicroservice\target\PaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\payment_microservice\PaymentMicroservice\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\payment_microservice\PaymentMicroservice\target\classes\stores\truststore.jks
pause
echo PaymentMicroservice is running!

start "NewPaymentMicroservice" java -jar %CD%\NewPaymentMicroservice\target\NewPaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\payment_microservice\PaymentMicroservice\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\payment_microservice\PaymentMicroservice\target\classes\stores\truststore.jks
pause
echo PaymentMicroservice is running!

:: start "PayPalMicroservice" java -jar %CD%\pay_pal_microservice\PayPalMicroservice\target\PayPalMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\pay_pal_microservice\PayPalMicroservice\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\pay_pal_microservice\PayPalMicroservice\target\classes\stores\truststore.jks
:: pause
:: echo PayPalMicroservice is running!

:: start "BitcoinMicroservice" java -jar %CD%\bitcoin_microservice\BitcoinMicroservice\target\BitcoinMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\bitcoin_microservice\BitcoinMicroservice\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\bitcoin_microservice\BitcoinMicroservice\target\classes\stores\truststore.jks
:: pause
:: echo BitcoinMicroservice is running!

start "CardPaymentMicroservice" java -jar %CD%\CardPaymentMicroservice\target\CardPaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\card_payment_microservice\CardPaymentMicroservice\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\card_payment_microservice\CardPaymentMicroservice\target\classes\stores\truststore.jks
pause
echo CardPaymentMicroservice is running!

start "PCC" java -jar %CD%\PCC\target\PCC-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\PCC\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\PCC\target\classes\stores\truststore.jks
pause
echo PCC is running!

start "Bank1" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\truststore.jks --bin=12345 --server.port=8080 --bank-name=Banka_intesa
pause
echo Bank1 is running!

::start "Bank2" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\truststore.jks --bin=13549 --server.port=8082 --bank-name=Vojvodjanska_banka
:: pause
:: echo Bank2 is running!

start "Bank3" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\truststore.jks --bin=17951 --server.port=8087 --bank-name=Unicredit_banka
pause
echo Bank3 is running!

start "ScienceCenter" java -jar %CD%\..\..\science_center_workspace\ScienceCenterRepository\ScienceCenter\target\ScienceCenter-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\..\..\science_center_workspace\ScienceCenterRepository\ScienceCenter\target\classes\stores\keystore.jks --server.ssl.trust-store=file:\%CD%\..\..\science_center_workspace\ScienceCenterRepository\ScienceCenter\target\classes\stores\truststore.jks --scientific-papers=file:\%CD%\..\..\science_center_workspace\ScienceCenterRepository\ScienceCenter\target\classes\scientific_papers
pause
echo ScienceCenter is running!

pause