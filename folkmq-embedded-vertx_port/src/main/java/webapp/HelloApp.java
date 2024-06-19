package webapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import webapp.folkmq.FolkMqConfig;

public class HelloApp extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new HelloApp());
        vertx.deployVerticle(new FolkMqConfig());
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.get("/").handler(req -> {
            req.response().putHeader("content-type", "text/plain")
                    .end("Hello world: " + req.request().getParam("name"));
        });

        vertx.createHttpServer().requestHandler(router::handle).listen(8080);
    }
}
