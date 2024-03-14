package com.example.cruduser.models;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> likeName(String name){
        if(name == null){
            return  null;
        }
        return  ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%"+name+"%"));
    }

    public static Specification<User> likeEmail(String email){
        if(email == null){
            return  null;
        }
        return  ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"),"%"+email+"%"));
    }
}
