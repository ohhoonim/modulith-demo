package dev.ohhoonim.modulith_demo.orders;

import java.util.Set;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
class OrderController {
   private final OrderService orderService;

   OrderController(OrderService orderService) {
      this.orderService = orderService;
   }

   @PostMapping
   void placeOrder(@RequestBody Order order) {
      orderService.placeOrder(order);
   }
}

@Service
@Transactional
class OrderService {
   private final OrderRepository orderRepository;
   private final ApplicationEventPublisher publisher;

   OrderService(OrderRepository orderRepository, ApplicationEventPublisher publisher) {
      this.orderRepository = orderRepository;
      this.publisher = publisher;
   }

   void placeOrder(Order order) {
      var saved = orderRepository.save(order);
      System.out.println("saved order: " + saved);
      saved.lineItems()
         .stream()
         .map(li -> new OrderPlacedEvent(li.id(), li.quantity(), li.product()))
         .forEach(publisher::publishEvent);
         

   }
}

interface OrderRepository extends ListCrudRepository<Order, Integer> {
}

@Table("orders")
record Order(@Id Integer id, Set<LineItem> lineItems) {
}

@Table("orders_line_items")
record LineItem(@Id Integer id, int product, int quantity) {
}
