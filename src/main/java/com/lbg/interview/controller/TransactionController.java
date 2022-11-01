package com.lbg.interview.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.lbg.interview.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.interview.data.TransactionRepository;
import com.lbg.interview.model.ResourceNotFoundException;
import com.lbg.interview.model.dto.TransactionDTO;
import com.lbg.interview.model.entity.Transaction;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionService transactionService;

    /**
     * @param unique
     * @return A {@link List} of {@link TransactionDTO} objects.
     */
    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(@RequestParam(value = "unique", defaultValue = "false") boolean unique) {
        final List<Transaction> transactionList = transactionRepository.findAll();

//        1.  use native query on top of findAll method
        //2. if not a query, implement a method with the help of datastructure and which adds a another
        // iteration which is timeconsuming

        //1. Asynchronous programming using CompletableFuture with Executors involing multiple threads and using thenReturning() method in CompletableFuture
        //2. which is being stored in


        final List<TransactionDTO> transactionDTOList = transactionList.stream()
                .map(txn -> modelMapper.map(txn, TransactionDTO.class))
                .collect(Collectors.toList());
        /*
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(TransactionDTO::getTransactionType) )), ArrayList::new))
                .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingDouble(TransactionDTO::getAmount) )),
                        ArrayList::new))
                .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(TransactionDTO::getDateTime) )),
                        ArrayList::new))
                .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(TransactionDTO::getText) )),
                        ArrayList::new))
         */

        //Right approach:
                HashSet<TransactionDTO> listHashSet = new HashSet<>();
                listHashSet.addAll(transactionDTOList);

        return transactionDTOList;
    }

    /**
     * Saves a new transaction using the specified details.
     *
     * @param transactionDTO
     * @return The {@link TransactionDTO} that was created.
     */
    @PostMapping("/transactions")
    public TransactionDTO saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        final Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        final Transaction savedTransaction = transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionDTO.class);
    }

    /**
     * @param transactionId
     * @return The {@link TransactionDTO} that corresponds to the specified transactionId.
     * @throws ResourceNotFoundException If a {@link Transaction} with a corresponding transactionId is not found.
     */
    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransactionById(@PathVariable(value = "id") long transactionId) throws ResourceNotFoundException {
        final Transaction transaction = transactionRepository.findById(transactionId).get();
        return modelMapper.map(transaction, TransactionDTO.class);
    }

}
