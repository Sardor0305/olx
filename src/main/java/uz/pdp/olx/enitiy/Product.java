package uz.pdp.olx.enitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import uz.pdp.olx.configuration.auditing.EntityAuditing;
import uz.pdp.olx.enam.ItemCondition;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product extends EntityAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

//    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;

    private Double price;

    private Boolean isActive = true;

    private Float ratingValue;

}