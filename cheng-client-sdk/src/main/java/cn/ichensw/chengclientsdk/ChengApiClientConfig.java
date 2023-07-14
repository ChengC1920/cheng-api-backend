package cn.ichensw.chengclientsdk;

import cn.ichensw.chengclientsdk.client.ChengApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Cheng API 客户端配置类
 * @author csw
 */
@Data
@Configuration
@ConfigurationProperties("cheng.client")
@ComponentScan
public class ChengApiClientConfig {

    private String accessKey;

    private String secretKey;

    /**
     * 此处方法取名无所谓的，不影响任何地方
     *
     * @return
     */
    @Bean
    public ChengApiClient getApiClient() {
        return new ChengApiClient(accessKey, secretKey);
    }
}
