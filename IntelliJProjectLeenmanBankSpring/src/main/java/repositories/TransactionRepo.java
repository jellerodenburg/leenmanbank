package repositories;

import model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

// TODO check if it needs to be a integer?
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    // JpaRepository add to be able to add pageable function
    // add pageable to retrieve a certain amount of data
    // public Pageable

    //TODO get all transaction from bank Nr
    @Query("")
    List<Transaction> findALLTransactionByBankNr(String bankNumber);



}
