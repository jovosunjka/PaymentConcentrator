@echo off

 "%CD%" vraca putanju do trenutnog direktorijuma (current directory)

start "EurekaServer" java -jar %CD%\EurekaServer\target\EurekaServer-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\EurekaServer\target\classes\stores\keystore.p12
echo WAIT FOR THE EURKEA SERVER TO FINISH RUNNING...
pause
echo EurekaServer is running!

start "ProxyServer" java -jar %CD%\ProxyServer\target\ProxyServer-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\ProxyServer\target\classes\stores\keystore.p12
pause
echo ProxyServer is running!

 start "PaymentMicroservice" java -jar %CD%\payment_microservice\PaymentMicroservice\target\PaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\payment_microservice\PaymentMicroservice\target\classes\stores\keystore.p12
 pause
 echo PaymentMicroservice is running!

 start "PayPalMicroservice" java -jar %CD%\pay_pal_microservice\PayPalMicroservice\target\PayPalMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\pay_pal_microservice\PayPalMicroservice\target\classes\stores\keystore.p12
 pause
 echo PayPalMicroservice is running!

 start "BitcoinMicroservice" java -jar %CD%\bitcoin_microservice\BitcoinMicroservice\target\BitcoinMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\bitcoin_microservice\BitcoinMicroservice\target\classes\stores\keystore.p12
 pause
 echo BitcoinMicroservice is running!

:: start "CardPaymentMicroservice" java -jar %CD%\card_payment_microservice\CardPaymentMicroservice\target\CardPaymentMicroservice-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\card_payment_microservice\CardPaymentMicroservice\target\classes\stores\keystore.p12
:: pause
:: echo CardPaymentMicroservice is running!

:: start "Bank" java -jar %CD%\Bank\SEP-Banka\target\SEP-Banka-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\Bank\SEP-Banka\target\classes\stores\keystore.p12
:: pause
:: echo Bank is running!

:: start "ScienceCenter" java -jar %CD%\..\..\science_center_workspace\ScienceCenterRepository\ScienceCenter\target\ScienceCenter-0.0.1-SNAPSHOT.jar --server.ssl.key-store=file:\%CD%\..\..\science_center_workspace\ScienceCenterRepository\ScienceCenter\target\classes\stores\keystore.p12
:: pause
:: echo ScienceCenter is running!

pause