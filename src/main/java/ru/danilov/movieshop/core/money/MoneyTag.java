package ru.danilov.movieshop.core.money;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MoneyTag extends SimpleTagSupport {

    private Currency currency;

    private double amount;

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        switch (currency) {
            case RUS_RUBLES:
                out.println(amount);
                out.println(currency.getShortTitle());
                break;
            case US_DOLLARS:
                out.println(currency.getShortTitle() + amount);
                break;
        }
        out.println();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
