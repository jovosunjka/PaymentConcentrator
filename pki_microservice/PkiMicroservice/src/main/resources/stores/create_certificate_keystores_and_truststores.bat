@echo off

:: https://stackoverflow.com/questions/30634658/how-to-create-a-certificate-chain-using-keytool

:: create payment_concentrator_root_ca_main certficate in keystore.jks (validity=7300=20yeras*365)
:: Note here an extension with BasicaContraint (bc) created to show that it's a CA.
keytool -genkeypair -keyalg RSA -keysize 2048 -validity 7300 -alias payment_concentrator_root_ca_main -keystore keystore.jks -dname "CN=payment_concentrator_root_ca_main" -storepass key_store_pass -keypass key_store_pass -ext bc=ca:true -ext SAN=dns:localhost
keytool -keystore keystore.jks -storepass key_store_pass -alias payment_concentrator_root_ca_main -exportcert -rfc > payment_concentrator_root_ca_main.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias pki_frontend -keystore keystore.jks -dname "CN=pki_frontend" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias pki_frontend | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > pki_frontend.pem
cat payment_concentrator_root_ca_main.pem pki_frontend.pem > pki_frontend_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias pki_frontend -file pki_frontend_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias science_center_1 -keystore keystore.jks -dname "CN=science_center_1" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias science_center_1 | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > science_center_1.pem
cat payment_concentrator_root_ca_main.pem science_center_1.pem > science_center_1_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias science_center_1 -file science_center_1_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias science_center_frontend -keystore keystore.jks -dname "CN=science_center_frontend" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias science_center_frontend | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > science_center_frontend.pem
cat payment_concentrator_root_ca_main.pem science_center_frontend.pem > science_center_frontend_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias science_center_frontend -file science_center_frontend_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias eureka_server -keystore keystore.jks -dname "CN=eureka_server" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias eureka_server | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > eureka_server.pem
cat payment_concentrator_root_ca_main.pem eureka_server.pem > eureka_server_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias eureka_server -file eureka_server_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias proxy_server -keystore keystore.jks -dname "CN=proxy_server" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias proxy_server | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > proxy_server.pem
cat payment_concentrator_root_ca_main.pem proxy_server.pem > proxy_server_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias proxy_server -file proxy_server_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias payment_microservice -keystore keystore.jks -dname "CN=payment_microservice" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias payment_microservice | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > payment_microservice.pem
cat payment_concentrator_root_ca_main.pem payment_microservice.pem > payment_microservice_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias payment_microservice -file payment_microservice_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias payment_microservice_frontend -keystore keystore.jks -dname "CN=payment_microservice_frontend" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias payment_microservice_frontend | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > payment_microservice_frontend.pem
cat payment_concentrator_root_ca_main.pem payment_microservice_frontend.pem > payment_microservice_frontend_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias payment_microservice_frontend -file payment_microservice_frontend_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias card_payment_microservice -keystore keystore.jks -dname "CN=card_payment_microservice" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias card_payment_microservice | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > card_payment_microservice.pem
cat payment_concentrator_root_ca_main.pem card_payment_microservice.pem > card_payment_microservice_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias card_payment_microservice -file card_payment_microservice_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias paypal_microservice -keystore keystore.jks -dname "CN=paypal_microservice" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias paypal_microservice | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > paypal_microservice.pem
cat payment_concentrator_root_ca_main.pem paypal_microservice.pem > paypal_microservice_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias paypal_microservice -file paypal_microservice_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias paypal_microservice_frontend -keystore keystore.jks -dname "CN=paypal_microservice_frontend" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias paypal_microservice_frontend | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > paypal_microservice_frontend.pem
cat payment_concentrator_root_ca_main.pem paypal_microservice_frontend.pem > paypal_microservice_frontend_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias paypal_microservice_frontend -file paypal_microservice_frontend_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias bitcoin_microservice -keystore keystore.jks -dname "CN=bitcoin_microservice" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias bitcoin_microservice | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > bitcoin_microservice.pem
cat payment_concentrator_root_ca_main.pem bitcoin_microservice.pem > bitcoin_microservice_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias bitcoin_microservice -file bitcoin_microservice_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias bitcoin_microservice_frontend -keystore keystore.jks -dname "CN=bitcoin_microservice_frontend" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias bitcoin_microservice_frontend | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > bitcoin_microservice_frontend.pem
cat payment_concentrator_root_ca_main.pem bitcoin_microservice_frontend.pem > bitcoin_microservice_frontend_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias bitcoin_microservice_frontend -file bitcoin_microservice_frontend_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias bank -keystore keystore.jks -dname "CN=bank" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias bank | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > bank.pem
cat payment_concentrator_root_ca_main.pem bank.pem > bank_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias bank -file bank_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias bank_frontend -keystore keystore.jks -dname "CN=bank_frontend" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias bank_frontend | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > bank_frontend.pem
cat payment_concentrator_root_ca_main.pem bank_frontend.pem > bank_frontend_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias bank_frontend -file bank_frontend_chain.pem

keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 -alias pcc -keystore keystore.jks -dname "CN=pcc" -storepass key_store_pass -keypass key_store_pass
keytool -keystore keystore.jks -storepass key_store_pass -certreq -alias pcc | keytool -storepass key_store_pass -keystore keystore.jks -gencert -validity 365 -alias payment_concentrator_root_ca_main -ext SAN=dns:localhost -ext ku:c=dig,keyEncipherment -rfc > pcc.pem
cat payment_concentrator_root_ca_main.pem pcc.pem > pcc_chain.pem
keytool -keystore keystore.jks -storepass key_store_pass -importcert -alias pcc -file pcc_chain.pem

keytool -genkeypair -alias payment_concentrator_trust -keystore truststore.jks -dname "CN=payment_concentrator_trust" -storepass trust_store_pass -keypass trust_store_pass
keytool -delete -keystore truststore.jks -storepass trust_store_pass -alias payment_concentrator_trust
keytool -keystore truststore.jks -storepass trust_store_pass -importcert -file payment_concentrator_root_ca_main.pem -alias payment_concentrator_root_ca_main -noprompt

del "science_center_1.pem"
del "science_center_1_chain.pem"
del "payment_concentrator_root_ca_main.pem"