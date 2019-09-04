package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends MongoRepository<Route,String> {
    List<Route> findByRouteID(String routeID);
    List<Route> findByRouteName(String routeName);
}
