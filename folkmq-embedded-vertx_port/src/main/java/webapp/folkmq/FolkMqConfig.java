package webapp.folkmq;

import io.vertx.core.AbstractVerticle;
import org.noear.solon.Solon;

/**
 * 这个类独立一个目录，可以让 Solon 扫描范围最小化
 * */
public class FolkMqConfig extends AbstractVerticle {

    @Override
    public void start() {
        Solon.start(FolkMqConfig.class, new String[]{"--cfg=folkmq.yml"});
    }


    @Override
    public void stop() {
        if (Solon.app() != null) {
            Solon.stopBlock(false, Solon.cfg().stopDelay());
        }
    }
}
