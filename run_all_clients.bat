@echo off

start "pki-frontend" npm start --prefix pki_microservice\Client\application
echo pki-frontend is running!

start "payment-microservice-frontend" npm start --prefix payment_microservice\payment-microservice-frontend
echo payment-microservice-frontend is running!

start "PayPal_frontend" npm start --prefix pay_pal_microservice\PayPal
echo PayPal_frontend is running!

start "BitCoinFront" npm start --prefix bitcoin_microservice\BitCoinFront
echo BitCoinFront is running!

start "BankFront" npm start --prefix Bank\BankFront
echo BankFront is running!

start "science-center-frontend" npm start --prefix ..\..\science_center_workspace\ScienceCenterRepository\science-center-frontend
echo science-center-frontend is running!

pause
