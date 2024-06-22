package dev.ohhoonim.modulith_demo.products;

import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ohhoonim.modulith_demo.orders.OrderPlacedEvent;

@Service
@Transactional
class Products {
    private final ProductsCollaborator productsCollaborator;

    Products(ProductsCollaborator productsCollaborator) {
        this.productsCollaborator = productsCollaborator;
    } 

    @ApplicationModuleListener
    // @Async
    // @EventListener
    void on(OrderPlacedEvent ope) throws Exception {
        System.out.println("starting [" + ope + "]");
        Thread.sleep(5_000);
        this.productsCollaborator.takeOut(ope.order());
        System.out.println("stopping [" + ope + "]");
    }
}

@Component
class ProductsCollaborator {
    int takeOut(int id) {
        System.out.println("take out product : " + id);
        return id + id;
    }
}
