# CHURNAI 架构

版权所有 © 2026 上海如静知华信息科技有限公司。

Vue 3 管理端和 H5 工作台通过 JWT 调用 Spring Boot REST API。领域服务 `ChurnPredictionService` 组合复购间隔、订单频次、客诉、回款、互动和合同周期，输出流失概率、解释原因和挽留优先级；JPA 与 Flyway 管理 MySQL 数据，Docker Compose 负责本地编排。

生产落地时应接入企业 SSO、CRM、CDP、客服工单、合同与触达平台，并落实个人信息最小化、授权依据、人工复核和审计追踪。
