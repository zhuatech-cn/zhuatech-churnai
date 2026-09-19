/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.churnai;
import cn.zhuatech.churnai.service.ChurnPredictionService; import org.junit.jupiter.api.Test; import static org.assertj.core.api.Assertions.assertThat;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ChurnPredictionServiceTests {private final ChurnPredictionService service=new ChurnPredictionService();
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void escalatesDisengagedCustomer(){var r=service.predict(new ChurnPredictionService.Request("CUS-1088",102,0,4,42,22,1));assertThat(r.riskLevel()).isEqualTo("HIGH");assertThat(r.interventionPriority()).isEqualTo("P1");assertThat(r.reasons()).hasSizeGreaterThanOrEqualTo(4);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void keepsHealthyCustomerLowRisk(){var r=service.predict(new ChurnPredictionService.Request("CUS-2031",12,7,0,0,88,16));assertThat(r.riskLevel()).isEqualTo("LOW");assertThat(r.recommendedAction()).contains("常规");}}
