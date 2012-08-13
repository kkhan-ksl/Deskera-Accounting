/*
 * Copyright (C) 2012  Krawler Information Systems Pvt Ltd
 * All rights reserved.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

package com.krawler.hql.accounting;

import com.krawler.common.admin.Company;
import com.krawler.common.admin.KWLCurrency;
import java.util.Date;
import java.util.Set;


public class GoodsReceipt {
    private String ID;
    private String goodsReceiptNumber;
  //  private String vendorInvoiceNumber;
    private boolean autoGenerated;
    private String billFrom;
    private String shipFrom;
    private Date dueDate;
    private Date shipDate;
    private String memo;
    private Discount discount;
    private JournalEntry journalEntry;
    private boolean isExpenseType;
    private Set<GoodsReceiptDetail> rows;
    private Set<ExpenseGRDetail> expenserows;
    private JournalEntryDetail vendorEntry;
    private JournalEntryDetail shipEntry;
    private JournalEntryDetail otherEntry;
    private JournalEntryDetail taxEntry;
    private Company company;
    private KWLCurrency currency;
    private ExchangeRateDetails exchangeRateDetail;
    private double externalCurrencyRate;
    private boolean deleted;
    private Tax tax;
    private Vendor vendor;

   /* public String getVendorInvoiceNumber() {
        return vendorInvoiceNumber;
    }

    public void setVendorInvoiceNumber(String vendorInvoiceNumber) {
        this.vendorInvoiceNumber = vendorInvoiceNumber;
    }*/

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isAutoGenerated() {
        return autoGenerated;
    }

    public void setAutoGenerated(boolean autoGenerated) {
        this.autoGenerated = autoGenerated;
    }

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(JournalEntry journalEntry) {
        this.journalEntry = journalEntry;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBillFrom() {
        return billFrom;
    }

    public void setBillFrom(String billFrom) {
        this.billFrom = billFrom;
    }

    public String getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(String shipFrom) {
        this.shipFrom = shipFrom;
    }

    public String getGoodsReceiptNumber() {
        return goodsReceiptNumber;
    }

    public void setGoodsReceiptNumber(String goodsReceiptNumber) {
        this.goodsReceiptNumber = goodsReceiptNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public JournalEntryDetail getOtherEntry() {
        return otherEntry;
    }

    public void setOtherEntry(JournalEntryDetail otherEntry) {
        this.otherEntry = otherEntry;
    }

    public Set<GoodsReceiptDetail> getRows() {
        return rows;
    }

    public void setRows(Set<GoodsReceiptDetail> rows) {
        this.rows = rows;
    }

    public JournalEntryDetail getShipEntry() {
        return shipEntry;
    }

    public void setShipEntry(JournalEntryDetail shipEntry) {
        this.shipEntry = shipEntry;
    }

    public JournalEntryDetail getVendorEntry() {
        return vendorEntry;
    }

    public void setVendorEntry(JournalEntryDetail vendorEntry) {
        this.vendorEntry = vendorEntry;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    
    public JournalEntryDetail getTaxEntry() {
        return taxEntry;
    }

    public void setTaxEntry(JournalEntryDetail taxEntry) {
        this.taxEntry = taxEntry;
    }

    public KWLCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(KWLCurrency currency) {
        this.currency = currency;
    }

    public ExchangeRateDetails getExchangeRateDetail() {
        return exchangeRateDetail;
    }

    public void setExchangeRateDetail(ExchangeRateDetails exchangeRateDetail) {
        this.exchangeRateDetail = exchangeRateDetail;
    }

    public double getExternalCurrencyRate() {
        return externalCurrencyRate;
    }

    public void setExternalCurrencyRate(double externalCurrencyRate) {
        this.externalCurrencyRate = externalCurrencyRate;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Set<ExpenseGRDetail> getExpenserows() {
        return expenserows;
    }

    public void setExpenserows(Set<ExpenseGRDetail> expenserows) {
        this.expenserows = expenserows;
    }

    public boolean isIsExpenseType() {
        return isExpenseType;
    }

    public void setIsExpenseType(boolean isExpenseType) {
        this.isExpenseType = isExpenseType;
    }
    
}
