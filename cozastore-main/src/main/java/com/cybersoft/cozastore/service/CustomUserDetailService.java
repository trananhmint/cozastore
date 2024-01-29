package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

//    Exception : Quăng lỗi và kết thúc code
//    compare : so sánh
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);

        if(user == null){
//            Không tìm thấy user trong hệ thống
            throw new UsernameNotFoundException("User không tồn tại");
        }else{
//            Tạo ra user để so sánh chứng thực
            User user1 = new User(user.getUsername(),user.getPassword(),new ArrayList<>());
            return user1;
        }

    }

}
