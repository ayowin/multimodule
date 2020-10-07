package com.ouyangwei.multimodule.dao;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * @class: MybatisPlusGenerator
 * @brief: 为多模块spring boot项目定制的MybatisPlus代码生成器，
 *          读取common模块中的application-common.yml中的数据库配置信息，
 *          可为每张表生成Controller,Service,ServiceImpl,Mapper和Mapper.xml。
 *          使用建议：
 *              此类旨在解决mysql冗余配置的问题，因此在读取数据库配置时有唯一的
 *              读配置文件耦合（即本框架中的：application-common.yml)，希望将此
 *              类用于自己项目的朋友们需注意该问题。
 * @author: ouyangwei
 * @date: 2020.10.07
 */
public class MybatisPlusGenerator {

    /* 代码作者署名 */
    private static String author = "ouyangwei";

    /* 要执行代码生成的表，多个表用英文逗号隔开 */
    private static String tableNames = "user,order";

    /* 父包名 */
    private static String parentPackageName = "com.ouyangwei.multimodule";

    /* 模块包名，""为不需要模块 */
    private static String modulePackageName = "dao";

    /* controller生成与否 */
    private static boolean controllerIgnore = true;
    /* controller包名 */
    private static String controllerPackageName = "controllers";
    /* controller文件名规则,""为默认命名 */
    private static String controllerNameFormat = "";

    /* service生成与否 */
    private static boolean serviceIgnore = true;
    /* service包名 */
    private static String servicePackageName = "services";
    /* service文件名规则,""为默认命名 */
    private static String serviceNameFormat = "%sService";
    /* service implement包名 */
    private static String serviceImplPackageName = "services.impls";
    /* service implement文件名规则,""为默认命名 */
    private static String serviceImplNameFormat = "";

    /* entity包名 */
    private static String entityPackageName = "entities";
    /* entity文件名规则,""为默认命名 */
    private static String entityNameFormat = "";

    /* mapper包名 */
    private static String mapperPackageName = "mappers";
    /* mapper文件名规则,""为默认命名 */
    private static String mapperNameFormat = "";

    /**
     *  mapper xml包名
     *  注意：本项目需要自行将生成的xml文件移动到classpath: mappers文件夹中
     *  即：resources/mappers
     *  对应application.properties或application.yml中mybatis plus的mapper-locations值
     *  原因：生成在src/main/java及其子目录下的所有非*.java文件，都不会参与编译、打包到发
     *      布目录中（即：target目录），推荐将所有的非*.java文件（配置、资源等）放到reso-
     *      urces下，以免导致配置、资源等文件找不到的异常。
     **/
    private static String mapperXmlPackageName = "mappers";
    /* mapper xml文件名规则,""为默认命名 */
    private static String mapperXmlNameFormat = "";

    public static void main(String[] args) throws Exception {

        /* 代码生成器 */
        AutoGenerator autoGenerator = new AutoGenerator();

        /* 全局配置 */
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        projectPath += "/dao";
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(author);
        /* 生成完成是否打开输出目录: xxx/src/main/java */
        globalConfig.setOpen(false);
        autoGenerator.setGlobalConfig(globalConfig);
        /**
         *  命名格式配置
         *  命名格式请查官方文档：
         *  https://mp.baomidou.com/config/generator-config.html
         **/
        globalConfig.setEntityName(entityNameFormat);
        globalConfig.setMapperName(mapperNameFormat);
        globalConfig.setXmlName(mapperXmlNameFormat);
        globalConfig.setControllerName(controllerNameFormat);
        globalConfig.setServiceName(serviceNameFormat);
        globalConfig.setServiceImplName(serviceImplNameFormat);

        /* 数据源配置 */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        /* 读取common模块中的application-common.yml */
        String applicationYamlPath = System.getProperty("user.dir")
                + "/common/src/main/resources/"
                + "application-common.yml";
//        System.out.println(applicationYamlPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(applicationYamlPath));
        StringBuffer content = new StringBuffer();
        String line = "";
        line = bufferedReader.readLine();
        while (line != null){
            content.append(line);
            content.append('\n');
            line = "";
            line = bufferedReader.readLine();
        }
//        System.out.println(content.toString());
        Yaml applicationYaml = new Yaml();
        HashMap<String,Object> allYamlObject = applicationYaml.load(content.toString());
//        System.out.println(allYamlObject);
        HashMap<String,Object> springObject = (HashMap<String,Object>)allYamlObject.get("spring");
//        System.out.println(springObject);
        HashMap<String,Object> datasourceObject = (HashMap<String,Object>)springObject.get("datasource");
//        System.out.println(datasourceObject);
//        System.out.println(datasourceObject.get("url"));
//        System.out.println(datasourceObject.get("driver-class-name"));
//        System.out.println(datasourceObject.get("username"));
//        System.out.println(datasourceObject.get("password"));
        dataSourceConfig.setUrl(datasourceObject.get("url").toString());
        dataSourceConfig.setDriverName(datasourceObject.get("driver-class-name").toString());
        dataSourceConfig.setUsername(datasourceObject.get("username").toString());
        dataSourceConfig.setPassword(datasourceObject.get("password").toString());
        autoGenerator.setDataSource(dataSourceConfig);

        /* 包配置 */
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(parentPackageName);
        packageConfig.setModuleName(modulePackageName);
        packageConfig.setEntity(entityPackageName);
        packageConfig.setMapper(mapperPackageName);
        packageConfig.setXml(mapperXmlPackageName);
        packageConfig.setController(controllerPackageName);
        packageConfig.setService(servicePackageName);
        packageConfig.setServiceImpl(serviceImplPackageName);
        autoGenerator.setPackageInfo(packageConfig);

        /* 模板配置 */
        TemplateConfig templateConfig = new TemplateConfig();
        if(controllerIgnore){
            templateConfig.setController("");
        }
        if(serviceIgnore){
            templateConfig.setService("");
            templateConfig.setServiceImpl("");
        }
        autoGenerator.setTemplate(templateConfig);

        /* 策略配置 */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        /* 是否使用lombok */
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableNames.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();
    }

}
