package com.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserController {
    private static UserService userService = new UserService();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/users", new GetUsersHandler());
        server.createContext("/users/get", new GetUserByIdHandler());

        server.setExecutor(null);
        System.out.println("Server started at http://localhost:8080");
        server.start();
    }

    static class GetUsersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                List<User> users = userService.getAllUsers();
                String response = users.stream()
                        .map(user -> "{ \"id\": " + user.getId() + ", \"name\": \"" + user.getName() + "\", \"email\": \"" + user.getEmail() + "\" }")
                        .collect(Collectors.joining(", ", "[", "]"));

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class GetUserByIdHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                int id = Integer.parseInt(query.split("=")[1]);

                Optional<User> user = userService.getUserById(id);
                String response = user.map(u -> "{ \"id\": " + u.getId() + ", \"name\": \"" + u.getName() + "\", \"email\": \"" + u.getEmail() + "\" }")
                                      .orElse("{ \"error\": \"User not found\" }");

                exchange.sendResponseHeaders(user.isPresent() ? 200 : 404, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
