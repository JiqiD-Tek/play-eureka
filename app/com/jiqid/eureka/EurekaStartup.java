package com.jiqid.eureka;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import play.inject.ApplicationLifecycle;

@Singleton
public class EurekaStartup {

    @Inject
    public EurekaStartup(ApplicationLifecycle lifecycle,
                         ApplicationInfoManager applicationInfoManager,
                         EurekaClient eurekaClient
    ) {
        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        lifecycle.addStopHook(() -> {
            applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.DOWN);
            eurekaClient.shutdown();
            return CompletableFuture.completedFuture(null);
        });
    }
}
