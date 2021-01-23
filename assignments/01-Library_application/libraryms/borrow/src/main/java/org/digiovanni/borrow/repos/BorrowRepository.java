package org.digiovanni.borrow.repos;

import org.digiovanni.borrow.models.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends MongoRepository<Borrow, String> {
}
