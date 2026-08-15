/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.churnai.config;
import cn.zhuatech.churnai.model.*; import cn.zhuatech.churnai.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository orders,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
 var u1=units.save(new OperatingUnit("CUSTOMER-SUCCESS","客户成功中心","营销中心",180));var u2=units.save(new OperatingUnit("MEMBER-OPS","会员运营组","运营中心",120));var u3=units.save(new OperatingUnit("SERVICE-QA","服务体验组","客服中心",96));
 var t1=orders.save(new WorkRecord("CR-260815-018","CUS-028176","华东制造客户流失预警",u1,24,16,1,LocalDate.now().plusDays(1),WorkRecord.Status.RUNNING,"CRM+服务记录"));
 var t2=orders.save(new WorkRecord("CR-260815-021","SEG-VIP-03","高价值会员沉默识别",u2,18,8,0,LocalDate.now().plusDays(2),WorkRecord.Status.RUNNING,"交易+触达"));
 var t3=orders.save(new WorkRecord("CR-260815-026","CUS-045902","订阅续费风险预测",u1,12,0,0,LocalDate.now().plusDays(3),WorkRecord.Status.RELEASED,"合同+使用行为"));
 var t4=orders.save(new WorkRecord("CR-260814-015","SEG-SME-08","中小客户挽留复盘",u3,20,20,1,LocalDate.now(),WorkRecord.Status.COMPLETED,"工单+满意度"));
 resources.saveAll(List.of(new ResourceRegister("CRM-01","客户关系管理平台",u1,ResourceRegister.Status.RUNNING,98),new ResourceRegister("MODEL-02","流失概率模型",u2,ResourceRegister.Status.IDLE,95),new ResourceRegister("CDP-03","客户数据连接器",u2,ResourceRegister.Status.RUNNING,93),new ResourceRegister("TOUCH-04","客户触达服务",u3,ResourceRegister.Status.ALARM,86)));
 reviews.saveAll(List.of(new ReviewRecord("RV-260804-032",t1,"人工复核",6,0,ReviewRecord.Result.PASSED,"程越"),new ReviewRecord("RV-260804-011",t2,"质量检查",3,0,ReviewRecord.Result.PASSED,"许知"),new ReviewRecord("RV-260803-018",t4,"结果抽查",5,1,ReviewRecord.Result.FAILED,"程越"),new ReviewRecord("RV-260804-003",t3,"上线确认",4,0,ReviewRecord.Result.PENDING,"许知")));
 String demo=encoder.encode("Demo@2026");users.saveAll(List.of(new UserAccount("operator",demo,"许知",UserAccount.Role.DOMAIN_USER,"SEARCH-OPS"),new UserAccount("planner",demo,"程越",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"顾清",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));};}
}
