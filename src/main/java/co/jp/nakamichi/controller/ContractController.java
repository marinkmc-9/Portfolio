package co.jp.nakamichi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.jp.nakamichi.entity.Contract;
import co.jp.nakamichi.service.ContractService;

@Controller
@RequestMapping("contract")
public class ContractController {
    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("contractlist", service.getContractList());
        // contract/list.htmlに画面遷移
        return "contract/list";
    }

    // ----- 追加:ここから -----
    /** Contract登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Contract contract) { // ---新規登録
        // User登録画面に遷移
        return "contract/register";
    }

    /** Contract登録処理 */
    @PostMapping("/register")
    public String postRegister(Contract contract) { // ---Contract contractに新規登録のデータが登録される。
        // Contract登録（Mysqlのテーブルに保存）
        service.saveContract(contract);
        // 一覧画面にリダイレクト
        return "redirect:/contract/list";
    }
    // ----- 追加:ここまで -----

}
