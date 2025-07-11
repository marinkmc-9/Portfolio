package co.jp.nakamichi.service;

import java.util.List;
import java.util.Set;//追加

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; //Contractの登録

import co.jp.nakamichi.entity.Contract;
import co.jp.nakamichi.repository.ContractRepository;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository repository) {
        this.contractRepository = repository;
    }

    /** 全件を検索して返す:一覧表示*/
    public List<Contract> getContractList() {
        // リポジトリのfindAllメソッドを呼び出す
        return contractRepository.findAll();
    }

    /** Userを1件検索して返す （更新する情報を取得する）：Contractの更新*/
    public Contract getContract(Integer id) {
        return contractRepository.findById(id).get();
    }

    /** Contractの登録を行なう（Mysqlのpriceテーブルに保存）：Contractの登録*/
    @Transactional
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    // ----- 追加:ここから -----
    /** Contractの削除を行なう : 1件ずつ取り出して削除する*/
    @Transactional
    public void deleteContract(Set<Integer> idck) {
        for(Integer id : idck) {
            contractRepository.deleteById(id);
        }
    }
    // ----- 追加:ここまで -----

}


