package com.foodiecli.factory;

import com.foodiecli.repository.CustomerRepository;
import com.foodiecli.util.CsvReader;

public class Factory {

    public static CsvReader getCsvReader(){
        return Holder.CSV_READER;
    }

    public static CustomerRepository getCustomerRepository(){
        return Holder.CUSTOMER_REPOSITORY;
    }
    private static class Holder {
        private static final CsvReader CSV_READER = new CsvReader();

        private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepository();
    }
}

