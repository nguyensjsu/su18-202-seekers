package edu.sjsu.seekers.starbucks.dao;

import edu.sjsu.seekers.starbucks.model.order_details;
import java.util.Optional;

public interface OrderDetailsDAO {
    Optional<order_details> get(Integer id);
    void save(order_details orderLineItems);
}
