package co.jp.nakamichi.entity;

import org.hibernate.validator.constraints.Length;//追加：入力チェック

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;//追加：入力チェック
import jakarta.validation.constraints.NotEmpty;//追加：入力チェック
import jakarta.validation.constraints.NotNull;//追加：入力チェック
import lombok.Data;

@Data
@Entity
@Table(name = "price")
public class Contract {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 月日。10桁。null不許可 */
    @Column(length = 10, nullable = false)
    @NotEmpty //追加：入力チェック
    @Length(max=10) //追加：入力チェック
    private String yearmonth;

    /** 通貨。10桁。null不許可 */
    @Column(length = 10, nullable = false)
    @NotEmpty //追加：入力チェック
    @Length(max=10) //追加：入力チェック
    private String currency;

    /** 金額。6桁。null不許可 */
    @Column(length = 6, nullable = false)
    @NotNull//追加：入力チェック エラー回避の為入力チェック 0ではない
    @Min(1)//追加：入力チェック エラー回避の為入力チェック 価格は1以上
    private Integer price;

}