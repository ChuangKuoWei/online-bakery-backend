package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.database.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    // 查詢最後一筆訂單 (按照 ordersId 由大到小排序，取第一筆)
    Orders findTopByOrderByOrdersIdDesc();
}
