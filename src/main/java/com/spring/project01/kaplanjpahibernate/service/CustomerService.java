package com.spring.project01.kaplanjpahibernate.service;



import com.spring.project01.kaplanjpahibernate.data.dal.CustomerServiceApplicationDAL;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import com.spring.project01.kaplanjpahibernate.data.entity.PostalCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

@Service
@Transactional
public class CustomerService {
    private final CustomerServiceApplicationDAL customerServiceApplicationDAL;

    public CustomerService(CustomerServiceApplicationDAL customerServiceApplicationDAL) {
        this.customerServiceApplicationDAL = customerServiceApplicationDAL;
    }


    public Customer mapCustomerAndBook(Customer customer)
    {
        return doWorkForService(() ->customerServiceApplicationDAL.mapCustomerAndBookAndCardAndMovie(customer), "TodoService.saveTodo");
    }

    public PostalCode mapCustomerAndPostalCode(PostalCode postalCode) {

        return doWorkForService(() -> customerServiceApplicationDAL.mapCustomerAndPostalCode(postalCode), "BookServiceApplicationDAL.findBookByType");

    }


    public Customer mapCustomerAndBookUpdateV1(Customer customer) {
        return doWorkForService(() ->customerServiceApplicationDAL.mapCustomerAndBookV1(customer), "TodoService.saveTodo");
    }

    public Customer mapCustomerAndBookUpdateV2(Customer customer) {
        return doWorkForService(() ->customerServiceApplicationDAL.mapCustomerAndBookV2(customer), "TodoService.saveTodo");
    }


}
