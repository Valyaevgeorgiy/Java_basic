package com.example.wearshopdbspring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WearRepository extends JpaRepository<Wear, Long> {
}