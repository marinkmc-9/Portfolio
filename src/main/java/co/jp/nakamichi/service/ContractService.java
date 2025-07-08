package co.jp.nakamichi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 追加

import co.jp.nakamichi.entity.Contract;
import co.jp.nakamichi.repository.ContractRepository;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository repository) {
        this.contractRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Contract> getContractList() {
        // リポジトリのfindAllメソッドを呼び出す
        return contractRepository.findAll();
    }

    // ----- 追加:ここから -----
    /** Contractの登録を行なう（Mysqlのpriceテーブルに保存） */
    @Transactional
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }
    // ----- 追加:ここまで -----

}


