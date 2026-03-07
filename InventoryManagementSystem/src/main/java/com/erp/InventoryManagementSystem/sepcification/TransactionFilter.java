package com.erp.InventoryManagementSystem.sepcification;

import com.erp.InventoryManagementSystem.entity.Transactions;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionFilter {

    public static Specification<Transactions> filterTransaction(String searchValue) {

        return (root, query, criteriaBuilder) -> {

            if (searchValue == null || searchValue.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String searchPattern = "%" + searchValue.toLowerCase() + "%";

            List<Predicate> predicates = new ArrayList<>();

            // ---------- Transaction fields ----------
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("note")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("status").as(String.class)),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("transactionType").as(String.class)),
                    searchPattern));


            // ---------- User join ----------
            Join<Object, Object> userJoin = root.join("user", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(userJoin.get("email")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(userJoin.get("name")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(userJoin.get("phoneNumber")),
                    searchPattern));


            // ---------- Supplier join ----------
            Join<Object, Object> supplierJoin = root.join("supplier", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(supplierJoin.get("name")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(supplierJoin.get("contactInfo")),
                    searchPattern));


            // ---------- Product join ----------
            Join<Object, Object> productJoin = root.join("product", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(productJoin.get("name")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(productJoin.get("sku")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(productJoin.get("description")),
                    searchPattern));


            // ---------- Category join ----------
            Join<Object, Object> categoryJoin = root.join("category", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(categoryJoin.get("name")),
                    searchPattern));


            // ---------- Final return ----------
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}