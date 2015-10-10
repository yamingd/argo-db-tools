package com.argo.db.tools;

import com.argo.yaml.YamlTemplate;

import java.io.IOException;

/**
 * Created by yamingd on 9/17/15.
 */
public class ToolsConfig {

    public static final String confName = "prjinfo.yaml";

    public static ProjectInfo projectInfo;

    /**
     * 加载配置信息
     * @throws IOException
     */
    public synchronized static ProjectInfo load(String confName) throws IOException {
        if (null != projectInfo){
            return projectInfo;
        }

        projectInfo = YamlTemplate.load(ProjectInfo.class, confName);
        return projectInfo;
    }
}
