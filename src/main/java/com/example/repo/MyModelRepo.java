package com.example.repo;

import com.example.model.MyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyModelRepo extends JpaRepository<MyModel,Long> {
}
