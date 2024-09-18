package com.example.FrigoMiamBack.repositories;

import com.example.FrigoMiamBack.entities.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge, UUID> {
}
