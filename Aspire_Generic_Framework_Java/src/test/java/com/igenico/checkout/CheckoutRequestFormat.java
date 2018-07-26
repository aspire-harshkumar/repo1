package com.igenico.checkout;


public class CheckoutRequestFormat{

public class Checkout {
	
	  Order OrderObject;
	  HostedCheckoutSpecificInput HostedCheckoutSpecificInputObject;


	 // Getter Methods 
	  public Order getOrder() {
	    return OrderObject;
	  }

	  public HostedCheckoutSpecificInput getHostedCheckoutSpecificInput() {
	    return HostedCheckoutSpecificInputObject;
	  }

	 // Setter Methods 
	  public void setOrder( Order orderObject ) {
	    this.OrderObject = orderObject;
	  }

	  public void setHostedCheckoutSpecificInput( HostedCheckoutSpecificInput hostedCheckoutSpecificInputObject ) {
	    this.HostedCheckoutSpecificInputObject = hostedCheckoutSpecificInputObject;
	  }
	}
	
public class HostedCheckoutSpecificInput {
	  private String variant;
	  private String locale;


	 // Getter Methods 

	  public String getVariant() {
	    return variant;
	  }

	  public String getLocale() {
	    return locale;
	  }

	 // Setter Methods 

	  public void setVariant( String variant ) {
	    this.variant = variant;
	  }

	  public void setLocale( String locale ) {
	    this.locale = locale;
	  }
	}
	
public class Order {
	
	  AmountOfMoney AmountOfMoneyObject;
	  Customer CustomerObject;


	 // Getter Methods 
	  public AmountOfMoney getAmountOfMoney() {
	    return AmountOfMoneyObject;
	  }

	  public Customer getCustomer() {
	    return CustomerObject;
	  }

	 // Setter Methods 
	  public void setAmountOfMoney( AmountOfMoney amountOfMoneyObject ) {
	    this.AmountOfMoneyObject = amountOfMoneyObject;
	  }

	  public void setCustomer( Customer customerObject ) {
	    this.CustomerObject = customerObject;
	  }
	}

public class Customer {
	
	  private int merchantCustomerId;
	  BillingAddress BillingAddressObject;

	 // Getter Methods 
	  public int getMerchantCustomerId() {
	    return merchantCustomerId;
	  }

	  public BillingAddress getBillingAddress() {
	    return BillingAddressObject;
	  }

	 // Setter Methods 
	  public void setMerchantCustomerId( int merchantCustomerId ) {
	    this.merchantCustomerId = merchantCustomerId;
	  }

	  public void setBillingAddress( BillingAddress billingAddressObject ) {
	    this.BillingAddressObject = billingAddressObject;
	  }
	}

public class BillingAddress {
		
	  private String countryCode;

	 // Getter Methods 
	  public String getCountryCode() {
	    return countryCode;
	  }

	 // Setter Methods 
	  public void setCountryCode( String countryCode ) {
	    this.countryCode = countryCode;
	  }
	  
	}
	
public class AmountOfMoney {
	
	  private String currencyCode;
	  private int amount;


	 // Getter Methods 
	  public String getCurrencyCode() {
	    return currencyCode;
	  }

	  public int getAmount() {
	    return amount;
	  }

	 // Setter Methods 
	  public void setCurrencyCode( String currencyCode ) {
	    this.currencyCode = currencyCode;
	  }

	  public void setAmount( int amount ) {
	    this.amount = amount;
	  }
	}

}