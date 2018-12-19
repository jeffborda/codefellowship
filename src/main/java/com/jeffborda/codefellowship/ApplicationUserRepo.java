package com.jeffborda.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepo extends CrudRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String username);
}
