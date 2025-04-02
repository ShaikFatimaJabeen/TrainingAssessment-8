package com.api;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
	private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1, "John Doe", "john@example.com"));
        users.add(new User(2, "Jane Doe", "jane@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }
}
	

