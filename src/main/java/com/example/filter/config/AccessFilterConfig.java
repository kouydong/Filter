package com.example.filter.config;

import com.example.filter.AccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class AccessFilterConfig implements WebMvcConfigurer {

    // Filter에 포함되는 URL 주소(전체 URL로 설정)
    private static final String[] INCLUDE_PATHS = {
            "/*"
    };

    @Bean
    public FilterRegistrationBean<AccessFilter> filterBean() {

        // 필터 @Bean 등록 시 AccessFilter 클래스를 필터에 등록
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AccessFilter());

        // 배열로 정의된 URI 대해서 필터를 적용
        registrationBean.setUrlPatterns(Arrays.asList(INCLUDE_PATHS));
        // setOrder() 메서드를 통해 다중 필터 사용 시 우선순위를 부여
        // registrationBean.setOrder(1);

        return registrationBean;
    }

    /*
    @Bean
    public FilterRegistrationBean<SecondFilter> {

        // 필터 @Bean 등록 시 SecondFilter 클래스를 필터에 등록
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SecondFilter());

        // 배열로 정의된 URI 대해서 필터를 적용
        registrationBean.setUrlPatterns(Arrays.asList(INCLUDE_PATHS));

        setOrder() 메서드를 통해 다중 필터 사용 시 우선순위를 부여
        registrationBean.setOrder(2);
    }
    */
}
