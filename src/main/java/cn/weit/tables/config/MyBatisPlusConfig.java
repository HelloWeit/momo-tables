package cn.weit.tables.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cuicui
 * @Description:
 * @date 2020/6/8 10:16
 */
@Configuration
@MapperScan("com.cuicui.demomp.*.mapper")
public class MyBatisPlusConfig {
    /**
    *@Description 分页
    *@author Cuicui
    *@date 2020/6/8 10:18
    */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
