package com.cookiebutter.PersistenceHandlers;

import com.cookiebutter.Data.Transaction;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MEUrena on 9/29/16.
 * All rights reserved.
 */

@Stateless
public class TransactionService {

    public static int IdCounter = 0;

    private List<Transaction> transactions;

    public TransactionService() { }

    public boolean add(Transaction t) {
        boolean added = false;
        if (!getTransactions().contains(t)) {
            added = getTransactions().add(t);
            setTransactions(transactions);
        }

        return added;
    }

    public Transaction findById(int id) {
        for (Transaction t : transactions) {
            if (t.getId() == id) {
                return t;
            }
        }

        return null;
    }

    public boolean delete(Transaction t) {
        boolean deleted = transactions.remove(t);
        if (deleted) {
            setTransactions(transactions);
        }

        return deleted;
    }

    public boolean deleteById(int id) {
        for (Transaction t : transactions) {
            if (t.getId() == id) {
                transactions.remove(t);
                setTransactions(transactions);

                return true;
            }
        }

        return false;
    }

    public List<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
