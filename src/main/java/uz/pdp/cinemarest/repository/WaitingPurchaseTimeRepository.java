package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.WaitingPurchaseTime;

public interface WaitingPurchaseTimeRepository extends JpaRepository <WaitingPurchaseTime,Integer> {

    @Query(nativeQuery = true,value = "select wpt.minute\n" +
            "from waiting_purchase_time wpt")
    Integer getWaitingMinute();
}
