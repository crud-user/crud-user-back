package com.example.cruduser.dao.specification;

import com.example.cruduser.dao.criteria.UserCriteriaParametrage;
import com.example.cruduser.models.User;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserSpecificationParametrage implements Specification<User> {

    private UserCriteriaParametrage userCriteriaParametrage;
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(criteriaBuilder != null){
            if(userCriteriaParametrage.getEmail() != null && !userCriteriaParametrage.getEmail().isEmpty()){
//                Expression<String> path1 = root.<String>get("email");
//                Expression<String> lower1 = criteriaBuilder.lower(path1);
                System.out.println("email::"+userCriteriaParametrage.getEmail());
                predicates.add(criteriaBuilder.like(root.get("email"), "%"+userCriteriaParametrage.getEmail()+"%"));
            }
            if(userCriteriaParametrage.getUserName() != null && !userCriteriaParametrage.getUserName().isEmpty()){
                Expression<String> path = root.<String>get("userName");
                Expression<String> lower = criteriaBuilder.lower(path);
                predicates.add(criteriaBuilder.like(lower, "%"+userCriteriaParametrage.getUserName()+"%"));
            }

            if(userCriteriaParametrage.getDateNaissance() != null){
                predicates.add(criteriaBuilder.equal(root.get("dateNaissance"),userCriteriaParametrage.getDateNaissance() ));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
