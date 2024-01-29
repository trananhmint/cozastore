package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CountryEntity;
import com.cybersoft.cozastore.entity.OrderDetailEntity;
import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.entity.ids.OrderDetailIds;
import com.cybersoft.cozastore.exception.UserNotFoundException;
import com.cybersoft.cozastore.payload.request.OrderProductRequest;
import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.repository.OrderDetailRepository;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.IOrderService;
import com.cybersoft.cozastore.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTHelperUtils jwtHelperUtils;


    @Override
    public boolean addOrder(HttpServletRequest request, OrderRequest orderRequest) {
//        Bước 1: Lấy token
//        Bước 2: Giải mã lại token
//        Bước 3: username hoặc userid

//        Lấy token từ header
        String header = request.getHeader("Authorization");
        if(header == null || header.isEmpty()){
            throw new UserNotFoundException(500,"Bạn không có quyền sử dụng tính năng này");
        }
        String token = header.substring(7);
        String dataToken = jwtHelperUtils.validToken(token);
        //        query database và lấy ra user_id
        UserEntity userEntity = userRepository.findByUsername(dataToken);
        if(userEntity == null){
            throw new UserNotFoundException(500,"Bạn không có quyền sử dụng tính năng này");
        }


//        Thêm dữ liệu bảng order
        CountryEntity country = new CountryEntity();
        country.setId(orderRequest.getCountryId());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCountry(country);
        orderEntity.setUser(userEntity);

//        Khi save thành công thì thuộc tính id của OrderEntity sẽ có giá trị
        orderRepository.save(orderEntity);

//        Thêm tiếp dữ liệu cho bảng order detail
        for (OrderProductRequest data : orderRequest.getListProduct()) {

//            Gán giá trị cho key trong OrderDetailEntity
            OrderDetailIds ids = new OrderDetailIds();
            ids.setOrderId(orderEntity.getId());
            ids.setProductId(data.getId());

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setIds(ids);
            orderDetailEntity.setPrice(data.getPrice());
            orderDetailEntity.setQuantity(data.getQuantity());

            orderDetailRepository.save(orderDetailEntity);
        }

        return false;
    }

}
