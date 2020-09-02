package experiment.graphcustomobject;

import java.util.Objects;

public class MetroStation {
    private String lineName;
    private String stationName;
    
    public MetroStation(String lineName, String stationName) {
        this.lineName = lineName;
        this.stationName = stationName;
    }
    
    public MetroStation getByName(String name) {
        if (name.equals(lineName)) {
            return this;
        }
        return null;
    }
    
    public String getLineName() {
        return lineName;
    }
    
    public String getStationName() {
        return stationName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MetroStation)) {
            return false;
        }
        MetroStation that = (MetroStation) o;
        return Objects.equals(lineName, that.lineName) &&
                Objects.equals(stationName, that.stationName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(lineName, stationName);
    }
}
