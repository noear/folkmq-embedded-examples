package webapp.folkmq;

import org.noear.solon.Solon;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 这个类独立一个目录，可以让 Solon 扫描范围最小化
 * */
@Configuration
public class FolkMqConfig {
    @PostConstruct
    public void start() {
        Solon.start(FolkMqConfig.class, new String[]{"--cfg=folkmq.yml"});
    }

    @PreDestroy
    public void stop() {
        if (Solon.app() != null) {
            Solon.stopBlock(false, Solon.cfg().stopDelay());
        }
    }
}
