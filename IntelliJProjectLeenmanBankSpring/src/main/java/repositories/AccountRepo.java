package repositories;

import model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.awt.print.Pageable;
import java.util.List;

@RepositoryRestResource
public interface AccountRepo extends CrudRepository<Account, Long> {

    // find/get all account info by acount number
    Account findAccountByAccountNumber(@Param("accountNumber") int accountNumber);

    // find/get all account by user
    //TODO find bankAccount by user
    @Query ("")
    List<Account> findAccountByUserId(int userId);





}
