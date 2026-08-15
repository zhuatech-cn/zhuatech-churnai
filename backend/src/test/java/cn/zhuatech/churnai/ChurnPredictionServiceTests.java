/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.churnai;
import cn.zhuatech.churnai.service.ChurnPredictionService; import org.junit.jupiter.api.Test; import static org.assertj.core.api.Assertions.assertThat;
class ChurnPredictionServiceTests {private final ChurnPredictionService service=new ChurnPredictionService();
 @Test void escalatesDisengagedCustomer(){var r=service.predict(new ChurnPredictionService.Request("CUS-1088",102,0,4,42,22,1));assertThat(r.riskLevel()).isEqualTo("HIGH");assertThat(r.interventionPriority()).isEqualTo("P1");assertThat(r.reasons()).hasSizeGreaterThanOrEqualTo(4);}
 @Test void keepsHealthyCustomerLowRisk(){var r=service.predict(new ChurnPredictionService.Request("CUS-2031",12,7,0,0,88,16));assertThat(r.riskLevel()).isEqualTo("LOW");assertThat(r.recommendedAction()).contains("常规");}}
