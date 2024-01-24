package uz.pdp.olx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.olx.dto.ResultMessage;
import uz.pdp.olx.enitiy.Image;
import uz.pdp.olx.service.ImageService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/upload_file/{product_id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> upLoadFile(@RequestParam("file") MultipartFile file,
                                        @PathVariable Long product_id) {
        ResultMessage resultMessage = imageService.upLoadFile(file, product_id);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.CREATED
                        : HttpStatus.CONFLICT)
                .body(resultMessage);
    }

    @GetMapping("/view/{image_id}")
    ResponseEntity<?> viewFile(@PathVariable Long image_id) {
        Image image = imageService.getFindById(image_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getContentType()));
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("nmadir").build());
        return new ResponseEntity<>(imageService.getData(image.getImagePath()),
                headers, HttpStatus.OK);
    }

    @GetMapping("/download/{image_id}")
    ResponseEntity<?> downloadFile(@PathVariable Long image_id) {
        Image image = imageService.getFindById(image_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getContentType()));
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("nmadir").build());
        return new ResponseEntity<>(imageService.getData(image.getImagePath()),
                headers, HttpStatus.OK);
    }

}
