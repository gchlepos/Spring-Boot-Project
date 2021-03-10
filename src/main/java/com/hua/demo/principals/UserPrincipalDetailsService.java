package com.hua.demo.principals;

import com.hua.demo.model.Person;
import com.hua.demo.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private PersonRepository personRepository;

    public UserPrincipalDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = this.personRepository.findByUsername(username);
        UserPrincipal citizenPrincipal = new UserPrincipal(person);

        return citizenPrincipal;
    }
}
