package org.test.teamproject_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.teamproject_back.dto.request.ReqReviewDto;
import org.test.teamproject_back.entity.Review;
import org.test.teamproject_back.repository.ReviewMapper;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.security.principal.PrincipalUser;

@Service
public class ReviewService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    @Transactional(rollbackFor = Exception.class)
    public void addReview(ReqReviewDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (userMapper.isProductOwned(principalUser.getId(), dto.getProductId()) == 0) {
            //리뷰 못씀
        }
        reviewMapper.addReview(dto.toReview(principalUser.getId()));
    }

    public Review getAllReviews() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return reviewMapper.findReviewByUserId(principalUser.getId());
    }
}
