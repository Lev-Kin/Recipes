package recipes.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.Recipe;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @NotNull
    @Email(regexp = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]+")
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, message = "Password size too short")
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "userCreate")
    private Set<Recipe> recipeCollection;
}


