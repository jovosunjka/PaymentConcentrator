package com.rmj.SEP.Banka.models;

import com.rmj.SEP.Banka.dto.BankAccountDTO;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "merchant_order_id", unique = false, nullable = false)
	private Long merchantOrderId; // ovo moze biti acquirerOrderId

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	private BankAccount magazineAccount;

	@Column(name = "consumer_card_holder", unique = false, nullable = true)
	private String consumerCardHolder;

	@Column(name = "consumer_exp_date", unique = false, nullable = true)
	private String consumerExpDate;

	@Column(name = "consumer_card_number", unique = false, nullable = true)
	private long consumerCardNumber;

	@Column(name = "consumer_security_code", unique = false, nullable = true)
	private int consumerSecurityCode;

	@Column(name = "amount", unique = false, nullable = false)
	private double amount;
    
	@Column(name = "currency", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;
    
    @Column(name = "merchant_timestamp", unique = false, nullable = false)
    private LocalDateTime merchantTimestamp; // ovo moze biti acquirerTimestamp
    
    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDateTime timestamp;
    
    @Column(name = "redirect_url", unique = false, nullable = true)
    private String redirectUrl;
    
    @Column(name = "callback_url", unique = false, nullable = false)
    private String callbackUrl;


	@Column(name = "status", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

	
	public Transaction() {
		
	}

	public Transaction(Long merchantOrderId, BankAccount magazineAccount, double amount,
					   Currency currency, LocalDateTime merchantTimestamp, String consumerCardHolder,
					   long consumerCardNumber, String consumerExpDate, int consumerSecurityCode,
					   String redirectUrl, String callbackUrl) {
		this.merchantOrderId = merchantOrderId;
		this.magazineAccount = magazineAccount;
		this.consumerCardHolder = consumerCardHolder;
		this.consumerCardNumber = consumerCardNumber;
		this.consumerExpDate = consumerExpDate;
		this.consumerSecurityCode = consumerSecurityCode;
		this.amount = amount;
		this.currency = currency;
		this.merchantTimestamp = merchantTimestamp;
		this.timestamp = LocalDateTime.now();
		this.redirectUrl = redirectUrl;
		this.callbackUrl = callbackUrl;
		this.status = TransactionStatus.READY;
	}

	public Transaction(Long merchantOrderId, BankAccount magazineAccount, double amount,
					   Currency currency, LocalDateTime merchantTimestamp, String redirectUrl, String callbackUrl) {
		this.merchantOrderId = merchantOrderId;
		this.magazineAccount = magazineAccount;
		this.consumerCardHolder = null;
		this.consumerCardNumber = -1;
		this.consumerExpDate = null;
		this.consumerSecurityCode = -1;
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

	public BankAccount getMagazineAccount() { return magazineAccount; }

	public void setMagazineAccount(BankAccount magazineAccount) { this.magazineAccount = magazineAccount; }

	public String getConsumerCardHolder() { return consumerCardHolder; }

	public void setConsumerCardHolder(String consumerCardHolder) { this.consumerCardHolder = consumerCardHolder; }

	public String getConsumerExpDate() {return consumerExpDate; }

	public void setConsumerExpDate(String consumerExpDate) { this.consumerExpDate = consumerExpDate; }

	public long getConsumerCardNumber() { return consumerCardNumber; }

	public void setConsumerCardNumber(long consumerCardNumber) { this.consumerCardNumber = consumerCardNumber; }

	public int getConsumerSecurityCode() { return consumerSecurityCode; }

	public void setConsumerSecurityCode(int consumerSecurityCode) {
		this.consumerSecurityCode = consumerSecurityCode;
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
