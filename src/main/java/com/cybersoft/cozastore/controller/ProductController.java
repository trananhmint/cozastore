package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.ProductResquest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.imp.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Base64;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByCategory(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        response.setData(iProductService.getProductByCategoryId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /**
     * Có 2 cách upload file chính:
     *
     * Cách 1: chuyển file về dạng base64
     * - Từ file chuyển thành chuỗi, rồi đẩy chuỗi lên server
     * - Từ chuỗi của file đã được chuyển, chuyển chuỗi đó lại thành file
     * ƯU ĐIỂM: Vì file đã chuyển thành chuỗi lên lưu trữ được dưới dạng chuỗi
     * NHƯỢC ĐIỂM: kích thước file sẽ tăng khoảng 1.5
     *
     *
     * Cách 2: Sử dụng multipartfile
     * -Mở một luồng đọc vào file (stream)
     *file.getBytes();
     * String base64 = Base64.
     *
     *
     * */

//    @PostMapping("")
//    public ResponseEntity<?> addProduct(MultipartFile file) throws Exception {
//        //Lấy tên file và đuôi file
//
////        byte[] filename = files.getBytes();
////        String base64 = Base64.getEncoder().encodeToString(filename);
//        String filename = file.getOriginalFilename();
//        String rootFolder = "D:\\FPT\\ki5\\FER201\\Exercise\\images";
//        Path pathRoot = Paths.get(rootFolder);
////      Nếu đường dẫn không tồn tại
//        if(!Files.exists(pathRoot)) {
////      Tạo folder
//            Files.createDirectory(pathRoot);
//        }
////      réolve = / =>   D:\FPT\kì 5\FER201\Exercise\images
//        Files.copy(file.getInputStream(), pathRoot.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
//        return new ResponseEntity<>(filename,HttpStatus.OK);
//    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@Valid ProductResquest productResquest){
        String filename = productResquest.getFile().getOriginalFilename();
        try {
            String rootFolder = "D:\\FPT\\ki5\\FER201\\Exercise\\images";
            Path pathRoot = Paths.get(rootFolder);
            if(!Files.exists(pathRoot)) {
                Files.createDirectory(pathRoot);
            }
            Files.copy(productResquest.getFile().getInputStream(), pathRoot.resolve(filename),StandardCopyOption.REPLACE_EXISTING);
            iProductService.addProduct(productResquest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(filename,HttpStatus.OK);
    }


}
