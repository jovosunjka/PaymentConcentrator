package com.rmj.PCC.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "acquirer_order_id", unique = false, nullable = false)
	private Long acquirerOrderId;

	@Column(name = "issuer_order_id", unique = false, nullable = true)
	private Long issuerOrderId;

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
	private Currency currency;

    @Column(name = "acquirer_bank_timestamp", unique = false, nullable = false)
    private LocalDateTime acquirerBankTimestamp;

	@Column(name = "issuer_bank_timestamp", unique = false, nullable = true)
	private LocalDateTime issuerBankTimestamp;
    
    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDateTime timestamp;

	@ManyToOne
	private Bank issuerBank;

    @ManyToOne
    private Bank acquirerBank;

	@Column(name = "status", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

	
	public Transaction() {
		
	}

	public Transaction(Long acquirerOrderId, String consumerCardHolder, String consumerExpDate,
					   long consumerCardNumber, int consumerSecurityCode, double amount, Currency currency,
					   LocalDateTime acquirerBankTimestamp, Bank issuerBank, Bank acquirerBank) {
		this.acquirerOrderId = acquirerOrderId;
		this.issuerOrderId = null;
		this.consumerCardHolder = consumerCardHolder;
		this.consumerExpDate = consumerExpDate;
		this.consumerCardNumber = consumerCardNumber;
		this.consumerSecurityCode = consumerSecurityCode;
		this.amount = amount;
		this.currency = currency;
		this.acquirerBankTimestamp = acquirerBankTimestamp;
		this.issuerBankTimestamp = null;
		this.timestamp = LocalDateTime.now();
		this.issuerBank = issuerBank;
		this.acquirerBank = acquirerBank;
		this.status = TransactionStatus.READY;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(Long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public String getConsumerCardHolder() {
		return consumerCardHolder;
	}

	public void setConsumerCardHolder(String consumerCardHolder) {
		this.consumerCardHolder = consumerCardHolder;
	}

	public String getConsumerExpDate() {
		return consumerExpDate;
	}

	public void setConsumerExpDate(String consumerExpDate) {
		this.consumerExpDate = consumerExpDate;
	}

	public long getConsumerCardNumber() {
		return consumerCardNumber;
	}

	public void setConsumerCardNumber(long consumerCardNumber) {
		this.consumerCardNumber = consumerCardNumber;
	}

	public int getConsumerSecurityCode() {
		return consumerSecurityCode;
	}

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

	public LocalDateTime getAcquirerBankTimestamp() {
		return acquirerBankTimestamp;
	}

	public void setAcquirerBankTimestamp(LocalDateTime acquirerBankTimestamp) {
		this.acquirerBankTimestamp = acquirerBankTimestamp;
	}

	public LocalDateTime getIssuerBankTimestamp() {
		return issuerBankTimestamp;
	}

	public void setIssuerBankTimestamp(LocalDateTime issuerBankTimestamp) {
		this.issuerBankTimestamp = issuerBankTimestamp;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Bank getIssuerBank() {
		return issuerBank;
	}

	public void setIssuerBank(Bank issuerBank) {
		this.issuerBank = issuerBank;
	}

	public Bank getAcquirerBank() {
		return acquirerBank;
	}

	public void setAcquirerBank(Bank acquirerBank) {
		this.acquirerBank = acquirerBank;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
}
