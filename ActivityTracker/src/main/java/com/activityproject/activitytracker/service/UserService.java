package com.activityproject.activitytracker.service;

import com.activityproject.activitytracker.model.Role;
import com.activityproject.activitytracker.model.User;

import com.activityproject.activitytracker.repository.RoleRepository;
import com.activityproject.activitytracker.repository.UserRepository;
import org.springframework.security.core.userdetails.*;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

    @Service
    @RequiredArgsConstructor
    @Slf4j

    public class UserService implements UserDetailsService {

        /**
         * Autowired UserRepository for database operations.
         */
        private final UserRepository userRepository;

        /**
         * Autowired RoleRepository for database operations.
         */

        private final RoleRepository roleRepository;

        /**
         * Injects a bean of type PasswordEncoder into this class.
         * The bean is used for encoding passwords before storing them.
         */

        private final PasswordEncoder passwordEncoder;

        /**
         * Loads the user by its username
         *
         * @param username the username to search for
         * @return the UserDetails object that matches the given username
         * @throws UsernameNotFoundException if the user with the given username is not found
         */
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // Retrieve user with the given username
            com.activityproject.activitytracker.model.User user = userRepository.findByUsername(username);
            // Check if user exists
            if (user == null) {
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            } else {
                log.info("User found in the database: {}", username);
                // Create a collection of SimpleGrantedAuthority objects from the user's roles
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                user.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                });
                // Return the user details, including the username, password, and authorities
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
            }
        }

        /**
         * Saves a new user to the database
         *
         * @param user the user to be saved
         * @return the saved user
         */

        public User saveUser(User user) {
                log.info("Saving new user {} to the database", user.getUsername());
                // Encode the user's password for security before saving
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }




        /**
         * Saves a new role to the database
         *
         * @param role the role to be saved
         * @return the saved role
         */

        public Role saveRole( Role role) {
            log.info("Saving new role {} to the database", role.getName());
            return roleRepository.save(role);
        }

        /**
         * Adds a role to the user with the given username
         *
         * @param username the username of the user to add the role to
         * @param roleName the name of the role to be added
         */

        public void addRoleToUser(String username, String roleName) {
            log.info("Adding role {} to user {}", roleName, username);

            // Retrieve the user and role objects from the repository
            com.activityproject.activitytracker.model.User user = userRepository.findByUsername(username);
            Role role = roleRepository.findByName(roleName);

            // Add the role to the user's role collection
            user.getRoles().add(role);

            // Save the user to persist the changes
            userRepository.save(user);
        }

        /**
         * Retrieves the user with the given username
         *
         * @param username the username to search for
         * @return the user with the given username
         */
        public com.activityproject.activitytracker.model.User getUser(String username) {
            log.info("Fetching user {}", username);
            return userRepository.findByUsername(username);
        }

        /**
         * Retrieves all users from the database
         *
         * @return a list of all users
         */

        public List<com.activityproject.activitytracker.model.User> getUsers() {
            log.info("Fetching all users");
            return userRepository.findAll();
        }

        public com.activityproject.activitytracker.model.User getUserByUsername(String name) {
            log.info("Retrieving user with username {}", name);
            return userRepository.findByUsername(name);
        }
    }

