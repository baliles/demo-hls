
package com.heroku.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="account", schema="salesforce" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="Account.countAll", query="SELECT COUNT(x) FROM Account x" )
} )
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="billingcountry", length=80)
    private String     billingcountry ;

    @Column(name="_hc_err", length=2147483647)
    private String     hcErr        ;

    @Column(name="description", length=2147483647)
    private String     description  ;

    @Column(name="tickersymbol", length=20)
    private String     tickersymbol ;

    @Column(name="_hc_lastop", length=32)
    private String     hcLastop     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastmodifieddate")
    private Date       lastmodifieddate ;

    @Column(name="fax", length=40)
    private String     fax          ;

    @Column(name="isdeleted")
    private Boolean    isdeleted    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate")
    private Date       createddate  ;

    @Column(name="sfid", length=18)
    private String     sfid         ;

    @Column(name="phone", length=40)
    private String     phone        ;

    @Column(name="billingpostalcode", length=20)
    private String     billingpostalcode ;

    @Column(name="billingstreet", length=255)
    private String     billingstreet ;

    @Column(name="website", length=255)
    private String     website      ;

    @Column(name="billingstate", length=80)
    private String     billingstate ;

    @Column(name="accountnumber", length=40)
    private String     accountnumber ;

    @Column(name="billinglongitude")
    private Double     billinglongitude ;

    @Column(name="billinglatitude")
    private Double     billinglatitude ;

    @Column(name="billingcity", length=40)
    private String     billingcity  ;

    @Column(name="accountsource", length=40)
    private String     accountsource ;

    @Column(name="name", length=255)
    private String     name         ;

    public Account update(Account a) {
		this.id = a.id;
		this.billingcountry = a.billingcountry;
		this.hcErr = a.hcErr;
		this.description = a.description;
		this.tickersymbol = a.tickersymbol;
		this.hcLastop = a.hcLastop;
		this.lastmodifieddate = a.lastmodifieddate;
		this.fax = a.fax;
		this.isdeleted = a.isdeleted;
		this.createddate = a.createddate;
		this.sfid = a.sfid;
		this.phone = a.phone;
		this.billingpostalcode = a.billingpostalcode;
		this.billingstreet = a.billingstreet;
		this.website = a.website;
		this.billingstate = a.billingstate;
		this.accountnumber = a.accountnumber;
		this.billinglongitude = a.billinglongitude;
		this.billinglatitude = a.billinglatitude;
		this.billingcity = a.billingcity;
		this.accountsource = a.accountsource;
		this.name = a.name;
		return a;
	}

	//----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Account() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : billingcountry ( varchar ) 
    public void setBillingcountry( String billingcountry ) {
        this.billingcountry = billingcountry;
    }
    public String getBillingcountry() {
        return this.billingcountry;
    }

    //--- DATABASE MAPPING : _hc_err ( text ) 
    public void setHcErr( String hcErr ) {
        this.hcErr = hcErr;
    }
    public String getHcErr() {
        return this.hcErr;
    }

    //--- DATABASE MAPPING : description ( text ) 
    public void setDescription( String description ) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    //--- DATABASE MAPPING : tickersymbol ( varchar ) 
    public void setTickersymbol( String tickersymbol ) {
        this.tickersymbol = tickersymbol;
    }
    public String getTickersymbol() {
        return this.tickersymbol;
    }

    //--- DATABASE MAPPING : _hc_lastop ( varchar ) 
    public void setHcLastop( String hcLastop ) {
        this.hcLastop = hcLastop;
    }
    public String getHcLastop() {
        return this.hcLastop;
    }

    //--- DATABASE MAPPING : lastmodifieddate ( timestamp ) 
    public void setLastmodifieddate( Date lastmodifieddate ) {
        this.lastmodifieddate = lastmodifieddate;
    }
    public Date getLastmodifieddate() {
        return this.lastmodifieddate;
    }

    //--- DATABASE MAPPING : fax ( varchar ) 
    public void setFax( String fax ) {
        this.fax = fax;
    }
    public String getFax() {
        return this.fax;
    }

    //--- DATABASE MAPPING : isdeleted ( bool ) 
    public void setIsdeleted( Boolean isdeleted ) {
        this.isdeleted = isdeleted;
    }
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }

    //--- DATABASE MAPPING : createddate ( timestamp ) 
    public void setCreateddate( Date createddate ) {
        this.createddate = createddate;
    }
    public Date getCreateddate() {
        return this.createddate;
    }

    //--- DATABASE MAPPING : sfid ( varchar ) 
    public void setSfid( String sfid ) {
        this.sfid = sfid;
    }
    public String getSfid() {
        return this.sfid;
    }

    //--- DATABASE MAPPING : phone ( varchar ) 
    public void setPhone( String phone ) {
        this.phone = phone;
    }
    public String getPhone() {
        return this.phone;
    }

    //--- DATABASE MAPPING : billingpostalcode ( varchar ) 
    public void setBillingpostalcode( String billingpostalcode ) {
        this.billingpostalcode = billingpostalcode;
    }
    public String getBillingpostalcode() {
        return this.billingpostalcode;
    }

    //--- DATABASE MAPPING : billingstreet ( varchar ) 
    public void setBillingstreet( String billingstreet ) {
        this.billingstreet = billingstreet;
    }
    public String getBillingstreet() {
        return this.billingstreet;
    }

    //--- DATABASE MAPPING : website ( varchar ) 
    public void setWebsite( String website ) {
        this.website = website;
    }
    public String getWebsite() {
        return this.website;
    }

    //--- DATABASE MAPPING : billingstate ( varchar ) 
    public void setBillingstate( String billingstate ) {
        this.billingstate = billingstate;
    }
    public String getBillingstate() {
        return this.billingstate;
    }

    //--- DATABASE MAPPING : accountnumber ( varchar ) 
    public void setAccountnumber( String accountnumber ) {
        this.accountnumber = accountnumber;
    }
    public String getAccountnumber() {
        return this.accountnumber;
    }

    //--- DATABASE MAPPING : billinglongitude ( float8 ) 
    public void setBillinglongitude( Double billinglongitude ) {
        this.billinglongitude = billinglongitude;
    }
    public Double getBillinglongitude() {
        return this.billinglongitude;
    }

    //--- DATABASE MAPPING : billinglatitude ( float8 ) 
    public void setBillinglatitude( Double billinglatitude ) {
        this.billinglatitude = billinglatitude;
    }
    public Double getBillinglatitude() {
        return this.billinglatitude;
    }

    //--- DATABASE MAPPING : billingcity ( varchar ) 
    public void setBillingcity( String billingcity ) {
        this.billingcity = billingcity;
    }
    public String getBillingcity() {
        return this.billingcity;
    }

    //--- DATABASE MAPPING : accountsource ( varchar ) 
    public void setAccountsource( String accountsource ) {
        this.accountsource = accountsource;
    }
    public String getAccountsource() {
        return this.accountsource;
    }

    //--- DATABASE MAPPING : name ( varchar ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(billingcountry);
        sb.append("|");
        sb.append(hcErr);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(tickersymbol);
        sb.append("|");
        sb.append(hcLastop);
        sb.append("|");
        sb.append(lastmodifieddate);
        sb.append("|");
        sb.append(fax);
        sb.append("|");
        sb.append(isdeleted);
        sb.append("|");
        sb.append(createddate);
        sb.append("|");
        sb.append(sfid);
        sb.append("|");
        sb.append(phone);
        sb.append("|");
        sb.append(billingpostalcode);
        sb.append("|");
        sb.append(billingstreet);
        sb.append("|");
        sb.append(website);
        sb.append("|");
        sb.append(billingstate);
        sb.append("|");
        sb.append(accountnumber);
        sb.append("|");
        sb.append(billinglongitude);
        sb.append("|");
        sb.append(billinglatitude);
        sb.append("|");
        sb.append(billingcity);
        sb.append("|");
        sb.append(accountsource);
        sb.append("|");
        sb.append(name);
        return sb.toString(); 
    } 

}
