/*
 * file: CoreModule.java
 * date: 17/07/11
 *
 * Copyright (c) 2017 jiqid.com, Inc. All Rights Reserved
 * This software is the confidential and proprietary information of jiqidao, Inc. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of
 * the license agreement you entered into with jiqidao.
 *
 */

import com.google.inject.AbstractModule;

import com.jiqid.eureka.EurekaStartup;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;

/**
 * PlayEureka Module
 *
 * @version        V1.0
 * @author         wangkangyou
 */
public class PlayEurekaModule extends AbstractModule {
    @Override
    protected void configure() {
        EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();

        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);
        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.STARTING);
        bind(ApplicationInfoManager.class).toInstance(applicationInfoManager);

        EurekaClientConfig clientConfig = new DefaultEurekaClientConfig();
        EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, clientConfig);
        bind(EurekaClient.class).toInstance(eurekaClient);
        bind(EurekaStartup.class).asEagerSingleton();
    }
}

