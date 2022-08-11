package recipes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotNull
    @NotBlank
    private String description;

    @Column(name = "ingredients")
    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "ingredients",
            joinColumns = @JoinColumn(name = "id")
    )
    @Size(min = 1)
    private List<String> ingredients;

    @Column(name = "directions")
    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "directions",
            joinColumns = @JoinColumn(name = "id")
    )
    @Size(min = 1)
    private List<String> directions;

    public Recipe(String name, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}

