package com.carcompare.license;

import org.springframework.stereotype.Component;

/**
 * 服务器信息提供类
 */
@Component
class ServerInfoProvider {
    ServerInfo serverInfo;

    public ServerInfo getServerInfo() {
        if(serverInfo == null){
            serverInfo = new ServerInfo();
            serverInfo.setMachineCode(MachineCodeGenerator.generate());
        }

        return serverInfo;
    }
}
