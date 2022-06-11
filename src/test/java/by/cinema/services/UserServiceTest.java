package by.cinema.services;

import by.cinema.entities.User;
import by.cinema.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {
    @Mock
    private static UserRepository userRepository;

    @InjectMocks
    private static UserService userService;

    @Mock
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername() {
        User user = new User(1L, "max", "555");
        when(userRepository.findByUsername("max")).thenReturn(user);
        userService.findByUsername("max");
        verify(userRepository, times(1)).findByUsername("max");
    }

    @Test
    void loadUserByUsername() {
        User user = new User(1L, "", "");
        when(userRepository.findByUsername("maks")).thenReturn(user);
        User max = userRepository.findByUsername("maks");
        assertNotNull(max);
        userService.loadUserByUsername("maks");
    }

    @Test
    void findUserById() {
        User user = new User(1L, "max", "555");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User userById = userService.findUserById(1L);
        assertEquals(user, userById);
    }

    @Test
    void allUsersWithFilter() {
    }

    @Test
    void SaveUser() {
        User user = new User(1L, "max", "555");
        when(userRepository.findByUsername("m")).thenReturn(user);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        User max = userRepository.findByUsername("m");
        max.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
        assertNotNull(max);
    }

    @Test
    void NotSaveUser() {
        User user = new User(1L, "m", "555");
        when(userRepository.findByUsername("m")).thenReturn(user);
        User max = userRepository.findByUsername("max");
        userService.saveUser(user);
        assertNull(max);
    }

    @Test
    void deleteUser() {
        User user = new User(1L, "max", "555");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> byId = userRepository.findById(1L);
        User user_log = userService.getUser_log();
        user_log.setId(5L);
        assertTrue(byId.isPresent());
        assertNotEquals(byId.get().getId(), user_log.getId());
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }
}