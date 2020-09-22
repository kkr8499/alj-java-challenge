package jp.co.axa.apidemo;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;

@Configuration
public class JacksonConfig {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            // setter,getter,is-getterを使用しない
            jacksonObjectMapperBuilder.visibility(PropertyAccessor.ALL, Visibility.NONE);
            // デシリアライズでpublicコンストラクタを使用する
            jacksonObjectMapperBuilder.visibility(PropertyAccessor.CREATOR, Visibility.PUBLIC_ONLY);
            // フィールドを使用する
            jacksonObjectMapperBuilder.visibility(PropertyAccessor.FIELD, Visibility.ANY);
            //pretty形式で出力
            jacksonObjectMapperBuilder.indentOutput(true);
        };
    }
}
