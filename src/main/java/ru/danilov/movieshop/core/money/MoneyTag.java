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
        String amountString = processAmount(amount);
        switch (currency) {
            case RUS_RUBLES:
                out.println(amountString);
                out.println(currency.getShortTitle());
                break;
            case US_DOLLARS:
                out.println(currency.getShortTitle() + amountString);
                break;
        }
        out.println();
    }

    private String processAmount(final Double amountDouble) {
        StringBuilder stringBuffer = new StringBuilder();
        String amountString = fmt(amountDouble);
        String[] arr = amountString.split(".");
        if (arr.length > 0) {
            amountString = arr[0];
        }
        int a = 0;
        for (int i = amountString.length() - 1; i >= 0; i--) {
            if (a != 0 && a % 3 == 0) {
                stringBuffer.insert(0, " ");
            }
            stringBuffer.insert(0, amountString.charAt(i));
            a++;
        }
        if (arr.length > 1) {
            stringBuffer.append(arr[2]);
        }
        return stringBuffer.toString();
    }

    public static String fmt(double d) {
        if(d == (int) d) {
            return String.format("%d",(int)d);
        } else {
            return String.format("%s",d);
        }
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
