package org.tinycloud.common.config.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数据库模型反向生成器
 * 可以参考代码自己本地运行即可
 * @author liuxingyu01
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        List<String> schemas = new ArrayList<>(); // 数据表列表
        schemas.add("t_uc_user");
        schemas.add("t_uc_role");
        schemas.add("t_ac_dict");
        schemas.add("t_ac_idtable");
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/tinycloud?serverTimezone=Asia/Shanghai",
                "root", "123654")
                //全局配置
                .globalConfig(builder -> {
                    builder.author("liuxingyu01")                                              // 设置作者
                            .enableSwagger()                                                // 开启 swagger 模式
                            .fileOverride()                                                 // 覆盖已生成文件
                            .disableOpenDir()                                               // 禁止打开输出目录
                            .dateType(DateType.ONLY_DATE)                                   // 时间策略
                            .commentDate("yyyy-MM-dd HH:mm:ss")                                      // 注释日期
                            .outputDir("D://data");                           // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.tinycloud.server")               // 设置父包名(也可以生成目录)
//                            .moduleName("log")                                     // 设置父包模块名
                            .entity("common.bean.entity")
                            .service("service")
                            .controller("controller")
                            .mapper("mapper")                                           // Mapper 包名
                            .xml("mapper")                                              // Mapper XML 包名
                            .pathInfo(Collections.singletonMap(
                                    OutputFile.xml, "D://data"));           // 设置mapperXml生成路径

                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(schemas)                                          // 设置需要生成的表名
                            .addTablePrefix("t_csp_")                                       // 表前缀过滤
                            .entityBuilder().idType(IdType.AUTO)                        //配置为数据库自增id类型
//                            .versionColumnName("version")                               // 乐观锁字段名(数据库)
//                            .logicDeleteColumnName("delFlag")                         // 逻辑删除字段名(数据库)
//                            .enableLombok()                                             // lombok生效
                            .enableTableFieldAnnotation()                               // 所有实体类加注解
                            .serviceBuilder()                                           // 切换至Service层设置
                            .formatServiceFileName("%sService")                         // 设定后缀名
                            .formatServiceImplFileName("%sServiceImpl");                // 设定后缀名
                })
                //模板配置
                .templateEngine(new FreemarkerTemplateEngine())                         // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
