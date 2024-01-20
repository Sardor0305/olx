package uz.pdp.olx.enitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "update_date")
public class UpdateDate {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private LocalDate createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private LocalDate updateTime;

    @NotNull
    @Column(name = "update_by", nullable = false)
    private Long updateBy;

}