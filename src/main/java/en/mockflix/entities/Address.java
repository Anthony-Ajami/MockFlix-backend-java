package en.mockflix.entities;

import javax.persistence.*;

@Entity
@Table(name="ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="ID")
    private Long id;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "AREA")
    private String area;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address [country=" + country + ", area=" + area + ", city=" + city + ", street=" + street + ", number=" + number + "]";
    }
}
