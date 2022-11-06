package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    List<Balloon> balloonList = new ArrayList<Balloon>(10);
    public static Order orders = new Order();

    public List<Balloon> findAllBalloons() {
        return balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloonList.stream().filter(a -> a.getDescription().contains(text) ||
                a.getName().contains(text)).collect(Collectors.toList());

    }
}
