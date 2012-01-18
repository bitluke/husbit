package com.bfs.husbit.model.embeddable;

import com.bfs.core.BaseSerializable;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import javax.enterprise.context.Dependent;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

/**
 * Created by IntelliJ IDEA.
 * User: lukman
 * Date: 1/11/12
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */

@Named
@Dependent
@Embeddable
public class MonetaryAmount implements BaseSerializable {
    
    private  BigDecimal amount ;
    private Currency currency = Currency.getInstance(Locale.getDefault());

    public MonetaryAmount() {
    }

    @Digits(integer=6 , fraction=2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MonetaryAmount)) return false;

		final MonetaryAmount monetaryAmount = (MonetaryAmount) o;

		if (!currency.equals(monetaryAmount.currency)) return false;
		if (!amount.equals(monetaryAmount.amount)) return false;

		return true;
	}

    @Override
	public int hashCode() {
		int result;
		result = amount.hashCode();
		result = 29 * result + currency.hashCode();
		return result;
	}

    @Override
    public String toString() {
		return getCurrency().getCurrencyCode()+ getAmount() ;
	}

}