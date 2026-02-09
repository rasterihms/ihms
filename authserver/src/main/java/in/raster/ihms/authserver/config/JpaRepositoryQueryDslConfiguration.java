/************************************************************************************************************
 *  CONFIDENTIAL AND PROPRIETARY
 *
 *  The source code and other information contained herein is the confidential and the exclusive property of
 *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
 *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
 *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 *  expressly permitted under such license agreement.
 *
 *  Copyright Raster Images Pvt. Ltd.
 *
 *  ALL RIGHTS RESERVED
 ************************************************************************************************************/
package in.raster.ihms.authserver.config;

import in.raster.ihms.commons.QueryDsl.ExtendedQueryDslJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration for QueryDsl with Jpql Query and pagination.
 */
@Configuration
@EnableJpaRepositories(basePackages = "in.raster.ihms.authserver", repositoryBaseClass = ExtendedQueryDslJpaRepositoryImpl.class)
public class JpaRepositoryQueryDslConfiguration {
}
