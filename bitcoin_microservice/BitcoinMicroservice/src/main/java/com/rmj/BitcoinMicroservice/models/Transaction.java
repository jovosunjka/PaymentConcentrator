package com.rmj.BitcoinMicroservice.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "merchant_order_id", unique = false, nullable = false)
	private Long merchantOrderId;
	
	@Column(name = "bitcoin_account_identifier", unique = false, nullable = true)
	private String bitcoinAccountIdentifier; // TODO ova klasa je prekopirana iz CardPaymentMicroservice-a, treba je malo doterati
	
	@Column(name = "first_Name", unique = false, nullable = true)
	private String firstName;
	
	@Column(name = "last_name", unique = false, nullable = true)
	private String lastName;
	
	@Column(name = "amount", unique = false, nullable = false)
	private double amount;
    
	@Column(name = "currency", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;
    
    @Column(name = "merchant_timestamp", unique = false, nullable = false)
    private LocalDateTime merchantTimestamp;
    
    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDateTime timestamp;
    
    @Column(name = "redirect_url", unique = false, nullable = false)
    private String redirectUrl;
    
    @Column(name = "callback_url", unique = false, nullable = false)
    private String callbackUrl;
    
	@Column(name = "status", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

	
	public Transaction() {
		
	}


	public Transaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime merchantTimestamp,
			String redirectUrl, String callbackUrl) {
		this.merchantOrderId = merchantOrderId;
		this.amount = amount;
		this.currency = currency;
		this.merchantTimestamp = merchantTimestamp;
		this.timestamp = LocalDateTime.now();
		this.redirectUrl = redirectUrl;
		this.callbackUrl = callbackUrl;
		this.status = TransactionStatus.PENDING;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getMerchantOrderId() {
		return merchantOrderId;
	}


	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}


	public String getBitcoinAccountIdentifier() {
		return bitcoinAccountIdentifier;
	}


	public void setBitcoinAccountIdentifier(String bitcoinAccountIdentifier) {
		this.bitcoinAccountIdentifier = bitcoinAccountIdentifier;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Currency getCurrency() {
		return currency;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public LocalDateTime getMerchantTimestamp() {
		return merchantTimestamp;
	}


	public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getRedirectUrl() {
		return redirectUrl;
	}


	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}


	public String getCallbackUrl() {
		return callbackUrl;
	}


	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}


	public TransactionStatus getStatus() {
		return status;
	}


	public void setStatus(TransactionStatus status) {
		this.status = status;
	}


	
		
}
