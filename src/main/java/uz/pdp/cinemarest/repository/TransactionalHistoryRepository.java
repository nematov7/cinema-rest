package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.TransactionalHistory;

public interface TransactionalHistoryRepository extends JpaRepository<TransactionalHistory, Integer> {


 //   String getPaymentIntentByTicketId(Integer integer);
}
