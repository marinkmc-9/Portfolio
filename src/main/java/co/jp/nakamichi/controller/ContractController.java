package co.jp.nakamichi.controller;

import java.util.Set;//Contractの削除

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 追加
import org.springframework.validation.annotation.Validated; // 追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;//Contractの登録
import org.springframework.web.bind.annotation.PathVariable;//Contractの更新
import org.springframework.web.bind.annotation.PostMapping;//Contractの登録
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;//Contractの削除

import co.jp.nakamichi.entity.Contract;//Contractの登録
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

    /** Contract登録画面を表示：Contractの登録 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Contract contract) {
        // User登録画面に遷移
        return "contract/register";
    }

    // ----- 変更ここから -----
    /** Contract登録処理：Contractの登録とエラーがあった場合は登録画面を表示 */
    @PostMapping("/register")
    public String postRegister(@Validated Contract contract, BindingResult res, Model model) {
        if(res.hasErrors()) {
            //エラーあった場合、メソッドを呼出して登録画面を表示：チェックリスト
            return getRegister(contract);
        }
        // Contract登録（Mysqlのテーブルに保存）
        service.saveContract(contract);
        // 一覧画面にリダイレクト
        return "redirect:/contract/list";
    }
    // ----- 変更ここまで -----

    /** Contract更新画面を表示：Contractの更新 */
    @GetMapping("/update/{id}/")
    public String getContract(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("contract", service.getContract(id));
        // Contract更新画面に遷移
        return "contract/update";
    }

    /** Contract更新処理：Contractの更新 */
    @PostMapping("/update/{id}/")
    public String postContract(Contract contract) {
        // Contract登録
        service.saveContract(contract);
        // 一覧画面にリダイレクト
        return "redirect:/contract/list";
    }

    /** Contract削除処理：Contractの削除 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Contractを一括削除
        service.deleteContract(idck);
        // 一覧画面にリダイレクト
        return "redirect:/contract/list";
    }

}
