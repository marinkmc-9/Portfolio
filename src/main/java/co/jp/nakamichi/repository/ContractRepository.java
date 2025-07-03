package co.jp.nakamichi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.jp.nakamichi.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}

