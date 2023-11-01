package hk.hku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kyleli
 * @ClassName AppMain
 */
@SpringBootApplication
@MapperScan("hk.hku.main.api.impl.mapper")
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class);
    }
}
