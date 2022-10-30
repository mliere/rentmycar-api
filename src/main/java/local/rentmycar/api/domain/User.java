package local.rentmycar.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@MappedSuperclass @Table(name="API_USER")
@DiscriminatorColumn(name = "ROLE", discriminatorType = DiscriminatorType.STRING)
@DynamicUpdate()
@AllArgsConstructor @NoArgsConstructor
public /*abstract*/ class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @Column(name="ROLE")
    @Getter @Setter
    private String role;

    @Column(name="FIRST_NAME")
    @Getter @Setter
    private String firstName;

    @Column(name="LAST_NAME")
    @Getter @Setter
    private String lastName;

    @Column(name="EMAIL_ADDRESS")
    @Getter @Setter
    private String mailAddress;

    @Column(name="ADDRESS")
    @Getter @Setter
    private String address;

    @Column(name="COUNTRY")
    @Getter @Setter
    private String country;

    @Column(name="STATE")
    @Getter @Setter
    private String state;

    @Column(name="PHONE_NUMBER")
    @Getter @Setter
    private String phoneNumber;

    @Lob @Column(name="PROFILE_PICTURE")
    @Getter @Setter
    private byte[] profilePicture;
}
