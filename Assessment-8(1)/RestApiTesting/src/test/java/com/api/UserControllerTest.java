package com.api;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserControllerTest {

    @Mock
    private UserService userService; 

    @InjectMocks
    private UserController userController; 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testGetAllUsers() {
        // Given: Mocking service response
        List<User> mockUsers = Arrays.asList(
            new User(1, "John Doe", "john@example.com"),
            new User(2, "Jane Doe", "jane@example.com")
        );

        when(userService.getAllUsers()).thenReturn(mockUsers); 

        // When: Call service
        List<User> users = userService.getAllUsers();

        // Then: Validate results
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
    }

    @Test
    void testGetUserById() {
        // Given: Mocking service response
        User mockUser = new User(1, "John Doe", "john@example.com");
        when(userService.getUserById(1)).thenReturn(Optional.of(mockUser));

        // When: Call service
        Optional<User> user = userService.getUserById(1);

        // Then: Validate response
        assertTrue(user.isPresent());
        assertEquals("John Doe", user.get().getName());
    }

    private Object when(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    void testAddUser() {
        // Given: Creating new user
        User newUser = new User(3, "Alice Smith", "alice@example.com");
        ((Object) when(userService.addUser(newUser))).thenReturn(newUser);

        // When: Adding user
        User addedUser = userService.addUser(newUser);

        // Then: Validate results
        assertNotNull(addedUser);
        assertEquals("Alice Smith", addedUser.getName());
    }
}
