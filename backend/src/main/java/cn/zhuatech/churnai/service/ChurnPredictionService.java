/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.churnai.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.math.*; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class ChurnPredictionService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Result predict(Request q){int score=0;List<String> reasons=new ArrayList<>();
  if(q.daysSinceLastOrder()>=90){score+=30;reasons.add("最近订单间隔超过九十天");}else if(q.daysSinceLastOrder()>=45){score+=15;reasons.add("复购周期正在拉长");}
  if(q.orderFrequency90Days()<=1){score+=25;reasons.add("近九十天下单频次偏低");}
  if(q.complaintCount90Days()>=3){score+=20;reasons.add("近期客诉次数偏高");}
  if(q.paymentDelayDays()>=30){score+=15;reasons.add("回款延迟影响合作稳定性");}
  if(q.engagementScore()<40){score+=20;reasons.add("客户互动活跃度偏低");}
  if(q.contractMonthsRemaining()<=1){score+=10;reasons.add("合同即将到期");}
  score=Math.min(100,score);String level=score>=65?"HIGH":score>=35?"MEDIUM":"LOW";
  BigDecimal probability=BigDecimal.valueOf(Math.min(0.96,0.08+score*0.009)).setScale(2,RoundingMode.HALF_UP);
  if(reasons.isEmpty())reasons.add("客户行为与服务指标保持稳定");
  String action=level.equals("HIGH")?"二十四小时内由客户成功负责人发起挽留计划":level.equals("MEDIUM")?"安排价值回顾并提供针对性续约方案":"保持常规经营触达";
  return new Result(q.customerCode(),score,level,probability,level.equals("HIGH")?"P1":level.equals("MEDIUM")?"P2":"P3",action,reasons);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotBlank String customerCode,@Min(0) int daysSinceLastOrder,@Min(0) int orderFrequency90Days,@Min(0) int complaintCount90Days,@Min(0) int paymentDelayDays,@Min(0) @Max(100) int engagementScore,@Min(0) int contractMonthsRemaining){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Result(String customerCode,int riskScore,String riskLevel,BigDecimal churnProbability,String interventionPriority,String recommendedAction,List<String> reasons){}
}
