package in.raster.ihms.abdm.domain.m1;

import in.raster.ihms.abdm.domain.enumeration.AbdmGender;

import java.util.List;

/**
 * Created by root on 13/6/24.
 */
public class AbdmV1ProfileSharePatient {

    private String healthId;
    private String healthIdNumber;
    private String name;
    private AbdmGender gender;
    private AbdmV1ProfileShareAddress address;
    private int yearOfBirth;
    private int dayOfBirth;
    private int monthOfBirth;
    private List<AbdmV1ProfileShareIdentifiers> identifiers;

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbdmGender getGender() {
        return gender;
    }

    public void setGender(AbdmGender gender) {
        this.gender = gender;
    }

    public AbdmV1ProfileShareAddress getAddress() {
        return address;
    }

    public void setAddress(AbdmV1ProfileShareAddress address) {
        this.address = address;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public List<AbdmV1ProfileShareIdentifiers> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<AbdmV1ProfileShareIdentifiers> identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public String toString() {
        return "AbdmV1ProfileSharePatient{" +
            "healthId='" + healthId + '\'' +
            ", healthIdNumber='" + healthIdNumber + '\'' +
            ", name='" + name + '\'' +
            ", gender=" + gender +
            ", address=" + address +
            ", yearOfBirth=" + yearOfBirth +
            ", dayOfBirth=" + dayOfBirth +
            ", monthOfBirth=" + monthOfBirth +
            ", identifiers=" + identifiers +
            '}';
    }
}
