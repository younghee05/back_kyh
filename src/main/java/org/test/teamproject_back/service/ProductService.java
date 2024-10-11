package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.test.teamproject_back.dto.request.ReqAddProductDto;
import org.test.teamproject_back.dto.request.ReqModifyProductDto;
import org.test.teamproject_back.dto.response.RespSearchProductDto;
import org.test.teamproject_back.entity.Category;
import org.test.teamproject_back.entity.Product;
import org.test.teamproject_back.repository.ProductMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public int addProduct(ReqAddProductDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!(authorities.contains("ROLE_ADMIN") || authorities.contains("ROLE_MANAGER"))) {
            throw new AuthenticationServiceException("생성 할 수 있는 권한이 없습니다.");
        }


        Product product = Product.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .origin(dto.getOrigin())
                .img(dto.getImg())
                .build();
        return productMapper.addProduct(product);
    }

    public RespSearchProductDto searchProduct(String title) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!(authorities.contains("ROLE_ADMIN") || authorities.contains("ROLE_MANAGER"))) {
            throw new AuthenticationServiceException("조회 할 수 있는 권한이 없습니다.");
        }
        System.out.println(title); // title은 가져옴
        Product response = productMapper.searchProductByTitle(title.trim()); // response가 안들어옴

        if (response == null) {
            throw new NullPointerException("해당 상품 정보가 존재하지 않습니다.");
        }

        RespSearchProductDto respDto = RespSearchProductDto.builder()
                .category(response.getCategories())
                .title(response.getTitle())
                .price(response.getPrice())
                .origin(response.getOrigin())
                .salesCount(response.getSalesCount())
                .stock(response.getStock())
                .createdDate(response.getCreatedDate())
                .build();

        return respDto;
    }

    public void deleteProduct(Long id) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!(authorities.contains("ROLE_ADMIN") || authorities.contains("ROLE_MANAGER"))) {
            throw new AuthenticationServiceException("삭제 할 수 있는 권한이 없습니다.");
        }
        productMapper.deleteProductById(id);
    }

    public void modifyProduct(ReqModifyProductDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<String> authorities = principalUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!(authorities.contains("ROLE_ADMIN") || authorities.contains("ROLE_MANAGER"))) {
            throw new AuthenticationServiceException("수정 할 수 있는 권한이 없습니다.");
        }

//        productMapper.updateProduct(dto.toEntity());
    }

}
