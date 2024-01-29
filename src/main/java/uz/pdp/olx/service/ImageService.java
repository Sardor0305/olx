package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.olx.dto.ImageDto;
import uz.pdp.olx.dto.ResultMessage;
import uz.pdp.olx.enitiy.Image;

import uz.pdp.olx.exception.NotFoundException;
import uz.pdp.olx.repository.ImageRepository;
import uz.pdp.olx.repository.ProductRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductRepository productRepo;
    private final ImageRepository imageRepository;

    @SneakyThrows
    public ResultMessage upLoadFile(MultipartFile multipartFile, Long productId) {
        if (multipartFile.getContentType() == null) {
            return new ResultMessage(false, "File failed to load");
        }
        String path = "src/main/resources/";
        String fileName = UUID.randomUUID() + ".png";
        File convertFile = new File(path + fileName);
//        convertFile.createNewFile();
        FileOutputStream file = new FileOutputStream(convertFile);
        file.write(multipartFile.getBytes());
        file.close();
        Image image = new Image();
        image.setImagePath(fileName);

        image.setProduct(productRepo.findById(productId)
                .orElseThrow(()-> new NotFoundException("product")));
        image.setContentType(multipartFile.getContentType());
        imageRepository.save(image);
        return new ResultMessage(true, new ImageDto(image));
    }

    public ImageDto getFindById(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow();
        System.out.println(imageId);
        return new ImageDto(image);
    }



    @SneakyThrows
    public byte[] getData(String imagePath) {
        File file = new File(imagePath);
        return Files.readAllBytes(file.toPath());
    }
}
