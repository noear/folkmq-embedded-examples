package webapp.folkmq;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.noear.solon.Solon;
import org.noear.solon.web.vertx.VxWebHandler;

/**
 * 这个类独立一个目录，可以让 Solon 扫描范围最小化
 * */
public class FolkMqConfig extends AbstractVerticle {
    private final Router router;
    private final VxWebHandler handler;

    public FolkMqConfig(Router router) {
        this.router = router;
        this.handler = new VxWebHandler();
    }

    @Override
    public void start() {
        router.routeWithRegex("/folkmq.*").handler(req -> {
            handler.handle(req.request());
        });

        Solon.start(FolkMqConfig.class, new String[]{"--cfg=folkmq.yml"});
    }

    @Override
    public void stop() {
        if (Solon.app() != null) {
            Solon.stopBlock(false, Solon.cfg().stopDelay());
        }
    }
}
