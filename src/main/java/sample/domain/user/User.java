package sample.domain.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from User u"),
        @NamedQuery(name = "User.findEmail", query = "select u from User u where u.email=:email "),
        @NamedQuery(name = "User.countEmail", query = "select count(u) from User u where u.email=:email "),
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Column(length = 256, nullable = false, unique = true)
    @NotNull
    @Size(min = 1, max = 256)
    private String email;

    @Column(length = 100, nullable = false)
    @NotNull
    @Size(min = 6, max = 100)
    private String passwd;

    @Column(name = "CRATE_DATE", nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date crateDate;

    @Column(nullable = false)
    @Version
    @NotNull
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
