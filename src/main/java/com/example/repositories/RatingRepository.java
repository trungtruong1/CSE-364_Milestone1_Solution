package com.example.repositories;
// import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Rating;


@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

}
