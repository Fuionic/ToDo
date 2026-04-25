package com.ToDomangemnet.Repository;

import com.ToDomangemnet.Entity.TODO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<TODO, Long> {


}
