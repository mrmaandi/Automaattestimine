package data;

import java.util.Date;

public interface DataModel {
    public String getCity();
    public Date getDate();
    public double getLowestTemp();
    public double getHighestTemp();
    public double getTemp();
    public double getLat();
    public double getLon();
}
