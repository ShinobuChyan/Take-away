package com.takeaway.repository.user;

import com.takeaway.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long>{
}
