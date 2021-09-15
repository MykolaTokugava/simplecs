package ua.metlife.claims.simplecs.entity.crl;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "languages")

@ToString(of = "{name, code}")
@EqualsAndHashCode(of = "{id}")
@Data
public class Languages {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column (name="id")
    private Long id;

    @NotBlank(message = "Language name cannot be empty")
    @Length(max = 100, message = "Name too long (more than 100)")
    @Column (name="name")
    private String name;

    @NotBlank(message = "Language code cannot be empty")
    @Length(max = 30, message = "Code too long (more than 30)")
    @Column (name="code")
    private String code;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
