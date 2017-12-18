package data;

import java.util.ArrayList;

public class InputDataList {
    private ArrayList<String> cities;

    public InputDataList(){
        this.cities = new ArrayList<>();
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void addToCities(String city){
        this.cities.add(city);
    }
}
