package in.raster.ihms.abdm.domain.m1;

/**
 * Created by root on 13/6/24.
 */
public class AbdmV1ProfileShareAddress {

    private String line;
    private String district;
    private String state;
    private String pincode;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "AbdmV1ProfileShareAddress{" +
            "line='" + line + '\'' +
            ", district='" + district + '\'' +
            ", state='" + state + '\'' +
            ", pincode='" + pincode + '\'' +
            '}';
    }
}
