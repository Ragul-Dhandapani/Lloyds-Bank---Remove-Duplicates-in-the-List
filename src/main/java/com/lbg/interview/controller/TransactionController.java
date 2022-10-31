package com.lbg.interview.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

    /**
     * @param unique
     * @return A {@link List} of {@link TransactionDTO} objects.
     */
    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(@RequestParam(value = "unique", defaultValue = "false") boolean unique) {
        final List<Transaction> transactionList = transactionRepository.findAll();
        final List<TransactionDTO> transactionDTOList = transactionList.stream()
                .map(txn -> modelMapper.map(txn, TransactionDTO.class))
                .collect(Collectors.toList());
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
