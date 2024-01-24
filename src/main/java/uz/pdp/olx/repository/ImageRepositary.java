package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.olx.enitiy.Image;

@Repository
public interface ImageRepositary extends JpaRepository<Image,Long> {
}
