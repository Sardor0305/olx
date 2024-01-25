package uz.pdp.olx.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.olx.dto.ResultMessage;
import uz.pdp.olx.enitiy.Image;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.repository.ImageRepositary;
import uz.pdp.olx.repository.ProductRepo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductRepo productRepo;
    private final ImageRepositary imageRepositary;

    @SneakyThrows
    public ResultMessage upLoadFile(MultipartFile multipartFile, Long productId) {
        if (multipartFile.getContentType() == null) {
            return new ResultMessage(false, "File failed to load");
        }
        String path = "src/main/resources/" + UUID.randomUUID();
        File convertFile = new File(path);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(multipartFile.getBytes());
        fout.close();
        Image image = new Image();
        image.setImagePath(path);


//        // Shuyaqqa Exception yaratib usha exceptionga otish kere
//        image.setProduct(productRepo.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product Id not found")));

        image.setContentType(multipartFile.getContentType());
        imageRepositary.save(image);
        return new ResultMessage(true, "File is upload successfully");
    }

    public Image getFindById(Long imageId) {
        return imageRepositary.findById(imageId).orElseThrow();
    }

    @SneakyThrows
    public byte[] getData(String imagePath)  {
        File file=new File(imagePath);
        return Files.readAllBytes(file.toPath());
    }
}
