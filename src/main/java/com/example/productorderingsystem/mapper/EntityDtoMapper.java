package com.example.productorderingsystem.mapper;

import com.example.productorderingsystem.exception.dto.*;
import com.example.productorderingsystem.exception.entity.*;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

import java.util.Locale.Category;
import java.util.stream.Collectors;
public class EntityDtoMapper {

    public UserDto mapUserToDtoBasic(User user){

     if (user == null) return null;
    UserDto userDto= new UserDto();
    userDto.setId(user.getId());
    userDto.setPhoneNumber(user.getPhoneNumber());
    userDto.setEmail(user.getEmail());
    userDto.setRole(user.getRole() != null ? user.getRole().name() : null);
    userDto.setname(user.getName());
    return userDto;

}
public AddressDto mapAddressToDtoBasic(Address address){
    if (address == null) return null;
    AddressDto addressDto = new AddressDto();
    addressDto.setId(address.getId());
    addressDto.setCity(address.getCity());
    addressDto.setStreet(address.getStreet());
    addressDto.setState(address.getState());
    addressDto.setCountry(address.getCountry());
    addressDto.setZipCode(address.getZipCode());
    return addressDto;
}
public CategoryDto mapCategoryToDtoBasic(Category category){
    if (category== null)return null;

    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setId(category.getId());
    categoryDto.setName(category.getName());
    return categoryDto;
}
public OrderItemDto mapOrderItemToDtoBasic(Orderitem orderItem){
    if (orderItem == null) return null;
    OrderItemDto OrderItemDto = new OrderItemDto();
    OrderItemDto.setId(orderItem.getId());
    OrderItemDto.setQuantity(orderItem.getQuantity());
    OrderItemDto.setprice(orderItem.getPrice());
    OrderItemDto.setState(orderItem.getState() !=null ? orderItem.getStatus().name() : null);
    OrderItemDto.setCreateAt(orderItem.getCreatedAt());
    return OrderItemDto;
}
public ProductDto mapproductToDtoBasic(Product Product){
    if (Product == null) return null;
    ProductDto  productDto = new ProductDto();
    productDto.setId(product.getId());
    productDto.setName(product.getName());
    productDto.setDescription(product.getDescription());
    productDto.setPrice(product.getPrice());
    productDto.setImageUrl(product.getImageUrl());
    return productDto;
}
public UserDto mapUserToDtoPlusAddress(User user){
    UserDto userDto = mapUserToDtoBasic(user);

    if(user !=null && user.getAddress() !=null){
        AddressDto addressDto = mapAddressToDtoBasic(user.getAddress());
        userDto.setAddress(addressDto);
    }
    return userDto;
}
public OrderItemDto mapOrderItemToPlusProduct(OrderItem orderItem){
    OrderItemDto OrderItemDto = mapOrderItemToDtoBasic(orderItem);
     if(orderitem !=null && orderItem.getProduct() !=null){
        ProductDto productDto = mapAddressToDtoBasic(orderItem.getProduct());
        OrderItemDto.setProduct(productDto);
     }
     return orderItemDto;
}
public OrderItemDto mapOrderItemToPlusProductAndUser(OrderItem orderItem){
    OrderItemDto OrderItemDto = mapOrderItemToDtoPlusProduct(orderItem);
     if(orderItem !=null && orderItem.getUser() !=null){
        UserDto userDto = mapUserToDtoPlusAddress(orderItem.getUser());
        OrderItemDto.setUser(userDto);
     }
     return orderItemDto;
}
public UserDto mapUserToDtoPlusAddressAndOrderHistory(User user){
    UserDto userDto  = mapUserToDtoPlusAddress(user);
     if(user !=null && user.getOrderItemList() !=null && !user.getOrderItemList().isEmpty()){
        userDto.setOrderItemList(user.getOrderItemList().stream().map(this::mapOrderItemToDtoPlusProduct).collect(Collectors.toList())); 
      
     }
     return userDto;
     
}
}
