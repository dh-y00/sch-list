package com.sch.list.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class InitApplication implements ApplicationRunner {

    private ListInitFactory listInitFactory;

    public InitApplication(ListInitFactory listInitFactory) {
        this.listInitFactory = listInitFactory;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        listInitFactory.init();
    }
}
