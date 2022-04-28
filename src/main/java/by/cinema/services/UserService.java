package by.cinema.services;

import by.cinema.entities.Role;
import by.cinema.entities.User;
import by.cinema.repositories.TicketRepository;
import by.cinema.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User user_log = new User();

    public User getUser_log() {
        return user_log;
    }


    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        user_log = user;
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsersWithFilter(String nameFilter) {
        if (nameFilter != null) {
            return userRepository.findAll().stream()
                    .filter(p -> p.getUsername().contains(nameFilter))
                    .collect(Collectors.toList());
        } else {
            return userRepository.findAll();
        }
    }

    @Transactional
    public boolean saveUser(User user) {
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        Date date = new Date();
        if (userFromDB.isEmpty()) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRegistration_date(date);
            userRepository.save(user);
            log.info("добавление пользователя " + user);
            return true;
        }
        return false;
    }

    public void deleteUser(Long userId) {
        if (!user_log.getId().equals(userId)) {
            userRepository.deleteById(userId);
            log.info("удаление пользователя по ID " + userId);
        }
    }
}
