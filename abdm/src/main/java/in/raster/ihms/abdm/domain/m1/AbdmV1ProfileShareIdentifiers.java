package in.raster.ihms.abdm.domain.m1;

import in.raster.ihms.abdm.domain.enumeration.AbdmIdentifiersType;

/**
 * Created by root on 13/6/24.
 */
public class AbdmV1ProfileShareIdentifiers {

    private AbdmIdentifiersType type;
    private String value;

    public AbdmIdentifiersType getType() {
        return type;
    }

    public void setType(AbdmIdentifiersType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AbdmV1ProfileShareIdentifiers{" +
            "type=" + type +
            ", value='" + value + '\'' +
            '}';
    }
}
