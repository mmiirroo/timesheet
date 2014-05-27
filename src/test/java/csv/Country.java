/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-27
 */

package csv;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Country {
    private String countryname;
    private String capital;

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("countryname", countryname);
        builder.append("capital", capital);
        return builder.toString();
    }
    
}
