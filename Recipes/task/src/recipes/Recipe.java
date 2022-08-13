package recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import recipes.security.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
public class Recipe {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "category")
    private String category;

    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @ElementCollection
    @CollectionTable
            (
                    name = "ingredients",
                    joinColumns = @JoinColumn(name = "id")
            )
    @Size(min = 1, message = "Provide at least one ingredient")
    @Column(name = "ingredients")
    private List<String> ingredients;

    @NotNull
    @ElementCollection
    @CollectionTable
            (
                    name = "directions",
                    joinColumns = @JoinColumn(name = "id")
            )
    @Size(min = 1, message = "Provide at least oen ingredient")
    @Column(name = "directions")
    private List<String> directions;

    @LastModifiedBy
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private User userCreate;

    public Recipe(String name, String category, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}



