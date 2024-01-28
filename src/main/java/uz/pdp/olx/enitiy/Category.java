package uz.pdp.olx.enitiy;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity

@Table(name = "_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Category parentCategory = null;


}
