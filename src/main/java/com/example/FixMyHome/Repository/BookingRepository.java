package com.example.FixMyHome.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FixMyHome.Model.BookedModel;

@Repository
public interface BookingRepository extends JpaRepository<BookedModel, Long>{
	List<BookedModel> findAllByUserIdOrderByBookingDateAsc(Long userId);
}
