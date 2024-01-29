package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.SignUpRequest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.LoginService;
import com.cybersoft.cozastore.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Tên file bên BE (Back End) sẽ đặt theo tên màn hình bên FE (Front End)
 *
 * Cấu trúc response chuẩn bên ngoài hay sử dụng
 *  statusCode: Mã lỗi của chương trình
 *  message: Những câu thông báo tương ứng với mã lỗi
 *  data: Dữ liệu trả ra nếu có ( đây là dữ liệu trả ra cho FE xử lý )
 *
 *  package payload : Chứa tất cả những file quy định request và response của toàn bộ project
 */
@RestController
@RequestMapping("/login")
//Cho phép domain khác gọi vào link API
//White List
//Black List
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTHelperUtils jwtHelperUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
//        boolean isSuccess = loginService.checkLogin(username,password);
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(user);

        String token = jwtHelperUtils.generateToken(username);

        BaseResponse response = new BaseResponse();
        response.setMessage("Đăng nhập thành công");
        response.setData(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    Viết API /signup cho tính năng đăng ký sử dụng RequestBody
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        boolean isSuccess = loginService.insertUser(signUpRequest);
        BaseResponse response = new BaseResponse();
        response.setMessage(isSuccess ? "Đăng ký thành công" : "Đăng ký thất bại");
        response.setData(isSuccess);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
