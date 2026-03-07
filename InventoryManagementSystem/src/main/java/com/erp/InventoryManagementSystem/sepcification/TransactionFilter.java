package com.erp.InventoryManagementSystem.sepcification;

import com.erp.InventoryManagementSystem.entity.Transactions;
import jakarta.persistence.criteria.Expression;
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
                    criteriaBuilder.lower(root.get("notes")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("transactionStatus").as(String.class)),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("transactionType").as(String.class)),
                    searchPattern));

            // ---------- User join ----------
            Join<Object, Object> userJoin = root.join("user", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(userJoin.get("userEmail")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(userJoin.get("userName")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(userJoin.get("userPhoneNumber")),
                    searchPattern));

            // ---------- Supplier join ----------
            Join<Object, Object> supplierJoin = root.join("supplier", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(supplierJoin.get("supplierName")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(supplierJoin.get("supplierContactInfo")),
                    searchPattern));

            // ---------- Product join ----------
            Join<Object, Object> productJoin = root.join("products", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(productJoin.get("productName")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(productJoin.get("productSku")),
                    searchPattern));

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(productJoin.get("productDescription")),
                    searchPattern));

            // ---------- Category join ----------
            Join<Object, Object> categoryJoin = root.join("category", JoinType.LEFT);

            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(categoryJoin.get("categoryName")),
                    searchPattern));

            // ---------- Final return ----------
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    // ---------- Filter by month and year ----------
    public static Specification<Transactions> byMonthAndYear(int month, int year) {

        return (root, query, criteriaBuilder) -> {

            Expression<Integer> monthExpression =
                    criteriaBuilder.function("month", Integer.class, root.get("createdAt"));

            Expression<Integer> yearExpression =
                    criteriaBuilder.function("year", Integer.class, root.get("createdAt"));

            Predicate monthPredicate = criteriaBuilder.equal(monthExpression, month);
            Predicate yearPredicate = criteriaBuilder.equal(yearExpression, year);

            return criteriaBuilder.and(monthPredicate, yearPredicate);
        };
    }
}