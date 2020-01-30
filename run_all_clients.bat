@echo off


start "payment-microservice-frontend" npm start --prefix payment_microservice\payment-microservice-frontend
echo payment-microservice-frontend is running!

:: start "PayPal_frontend" npm start --prefix pay_pal_microservice\PayPal
:: echo PayPal_frontend is running!

start "BitCoinFront" npm start --prefix bitcoin_microservice\BitCoinFront
echo BitCoinFront is running!

:: start "card_payment_microservice_BankFront" npm start --prefix card_payment_microservice\BankFront
:: echo card_payment_microservice_BankFront is running!

:: start "science-center-frontend" npm start --prefix ..\..\science_center_workspace\ScienceCenterRepository\science-center-frontend
:: echo science-center-frontend is running!

pause
