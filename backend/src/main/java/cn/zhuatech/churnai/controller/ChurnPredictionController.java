/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.churnai.controller;
import cn.zhuatech.churnai.common.ApiResponse; import cn.zhuatech.churnai.service.ChurnPredictionService; import jakarta.validation.Valid; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/ai/churn") @PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')") public class ChurnPredictionController {private final ChurnPredictionService service;/**
                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                            */
public ChurnPredictionController(ChurnPredictionService service){this.service=service;}/**
                                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                   */
@PostMapping("/predict") public ApiResponse<ChurnPredictionService.Result> predict(@Valid @RequestBody ChurnPredictionService.Request request){return ApiResponse.ok("客户流失风险预测完成",service.predict(request));}}
