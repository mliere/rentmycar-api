package local.rentmycar.api.data;

import javax.persistence.*;

@Entity
@Table(name="CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CAR_ID")
    private long id;

    @Column(name="LICENSE_PLATE_NUMBER")
    private String licensePlateNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                '}';
    }
}
