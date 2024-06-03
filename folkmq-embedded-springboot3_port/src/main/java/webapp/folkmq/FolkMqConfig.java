package webapp.folkmq;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.noear.solon.Solon;
import org.springframework.context.annotation.Configuration;

/**
 * 这个类独立一个目录，可以让 Solon 扫描范围最小化
 * */
@Configuration
public class FolkMqConfig {
    @PostConstruct
    public void start() {
        Solon.start(FolkMqConfig.class, new String[]{});
    }

    @PreDestroy
    public void stop() {
        if (Solon.app() != null) {
            Solon.stopBlock(false, Solon.cfg().stopDelay());
        }
    }
}
