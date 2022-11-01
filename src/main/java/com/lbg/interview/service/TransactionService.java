package com.lbg.interview.service;

import com.lbg.interview.model.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ComboBoxUI;

@Service
public class TransactionService implements Comparable {



    public boolean isSameTransaction(TransactionDTO transactionDTO){
            return true;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
