package com.liang.service.edu.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.liang.service.edu.entity.BaseEntity;
import org.junit.jupiter.api.Test;
import java.util.*;

public class AutoCodeGeneratorTest {

    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";  // 驱动名称
    private static final String password = "123456";  // 数据库用户密码
    private static final String url = "jdbc:mysql://42.193.122.64:3306/edu?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";  // 数据库连接URL
//    private static final String tableName = "edu_teacher";  // 待生成对象表名
    private static final String tableName = "edu_subject";
    private static final String parent = "com.liang.service";
    public static final String moduleName = "edu";

    @Test
    public void run(){
        String projectPath = System.getProperty("user.dir");
        // 数据库用户名
        String username = "root";
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username,password))
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("liang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 关闭资源管理器
                            .outputDir(projectPath + "/src/main/java") // 指定输出目录
                            .dateType(DateType.TIME_PACK); // 时间策略
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .controller("controller")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath+ "/src/main/resources/")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder ->
                    builder.addInclude(tableName.split(",")) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .serviceBuilder() //开启service策略配置
                            .formatServiceFileName("%sService") // 取消Service前的I
                            .controllerBuilder() // 开启 Controller 配置
                            .enableRestStyle() // 开启生成 @RestController 控制器
                            .enableHyphenStyle()// 开启驼峰转连字符
                            .entityBuilder()// 开启 Entity 配置
                            .idType(IdType.ASSIGN_ID) // 主键策略
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略，驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略，驼峰命名
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .enableLombok() // 开启lombok
                            .enableChainModel() // 开启lombok链式操作@Accessors(chain = true)
                            .logicDeleteColumnName("is_deleted") // 逻辑删除字段名
                            .enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT)) // 配置添加自动填充字段
                            .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))  // 添加和更新配置自动填充字段
                            .superClass(BaseEntity.class)
                            .addSuperEntityColumns("id", "gmt_create", "gmt_modified")
                )
                // 模板配置
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
