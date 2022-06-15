package pl.sb.security.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "web_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 5, max = 50)
    private String username;
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;
    @NotBlank
    @Size(max = 100)
    private String address;
    @NotBlank
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String postalCode;
    @NotBlank
    @Size(min = 2, max = 50)
    private String city;
    @Email
    @Size(min = 7, max = 80)
    private String email;
    @AssertTrue
    private boolean termsAgreement;
    @NotEmpty
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_and_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"))
    private Set<UserRole> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTermsAgreement() {
        return termsAgreement;
    }

    public void setTermsAgreement(boolean termsAgreement) {
        this.termsAgreement = termsAgreement;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}