package com.lbg.interview;

import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lbg.interview.data.TransactionRepository;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(StartApplication.class);

    @Autowired
    private TransactionRepository transactionRespository;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {
        LOGGER.info(() -> "Application started - Loaded " + transactionRespository.count() + " transactions.");
    }

}