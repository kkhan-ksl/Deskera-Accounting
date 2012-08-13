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
import java.util.Set;

/**
 *
 * @author krawler
 */
public class BillingPayment {

    private String ID;
    private String billingPaymentNumber;
    private boolean autoGenerated;
    private String memo;
    private JournalEntry journalEntry;
    private Set<BillingPaymentDetail> rows;
    private PayDetail payDetail;
    private Company company;
    private boolean deleted;
    private double externalCurrencyRate;
    private KWLCurrency currency;

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

    public String getBillingPaymentNumber() {
        return billingPaymentNumber;
    }

    public void setBillingPaymentNumber(String billingPaymentNumber) {
        this.billingPaymentNumber = billingPaymentNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public PayDetail getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(PayDetail payDetail) {
        this.payDetail = payDetail;
    }

    public Set<BillingPaymentDetail> getRows() {
        return rows;
    }

    public void setRows(Set<BillingPaymentDetail> rows) {
        this.rows = rows;
    }

    public KWLCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(KWLCurrency currency) {
        this.currency = currency;
    }

    public double getExternalCurrencyRate() {
        return externalCurrencyRate;
    }

    public void setExternalCurrencyRate(double externalCurrencyRate) {
        this.externalCurrencyRate = externalCurrencyRate;
    }
}