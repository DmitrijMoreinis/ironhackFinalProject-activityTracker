package com.activityproject.activitytracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.activityproject.activitytracker.model.Cycling;
import com.activityproject.activitytracker.model.Role;
import com.activityproject.activitytracker.model.Running;
import com.activityproject.activitytracker.model.User;
import com.activityproject.activitytracker.repository.RoleRepository;
import com.activityproject.activitytracker.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = userService.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        User user = new User("User found in the database: {}", "janedoe", "iloveyou", new ArrayList<>());
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = userService.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername(Mockito.<String>any());
    }


    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        Role role = new Role();
        role.setName("Name");
        role.setRole_id(1L);

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRoles()).thenReturn(roleList);
        doNothing().when(user).setBirthDate(Mockito.<Date>any());
        doNothing().when(user).setCreatedAt(Mockito.<Date>any());
        doNothing().when(user).setCyclingList(Mockito.<List<Cycling>>any());
        doNothing().when(user).setName(Mockito.<String>any());
        doNothing().when(user).setOverallDistanceCycling(anyFloat());
        doNothing().when(user).setOverallDistanceRunning(anyFloat());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRoles(Mockito.<Collection<Role>>any());
        doNothing().when(user).setRunningList(Mockito.<List<Running>>any());
        doNothing().when(user).setUpdatedAt(Mockito.<Date>any());
        doNothing().when(user).setUser_id(Mockito.<UUID>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        doNothing().when(user).setWeight(anyFloat());
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = userService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(user).getPassword();
        verify(user).getUsername();
        verify(user).getRoles();
        verify(user).setBirthDate(Mockito.<Date>any());
        verify(user).setCreatedAt(Mockito.<Date>any());
        verify(user).setCyclingList(Mockito.<List<Cycling>>any());
        verify(user).setName(Mockito.<String>any());
        verify(user).setOverallDistanceCycling(anyFloat());
        verify(user).setOverallDistanceRunning(anyFloat());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRoles(Mockito.<Collection<Role>>any());
        verify(user).setRunningList(Mockito.<List<Running>>any());
        verify(user).setUpdatedAt(Mockito.<Date>any());
        verify(user).setUser_id(Mockito.<UUID>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(user).setWeight(anyFloat());
    }

    /**
     * Method under test: {@link UserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        Role role = new Role();
        role.setName("Name");
        role.setRole_id(1L);

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRole_id(2L);

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role2);
        roleList.add(role);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRoles()).thenReturn(roleList);
        doNothing().when(user).setBirthDate(Mockito.<Date>any());
        doNothing().when(user).setCreatedAt(Mockito.<Date>any());
        doNothing().when(user).setCyclingList(Mockito.<List<Cycling>>any());
        doNothing().when(user).setName(Mockito.<String>any());
        doNothing().when(user).setOverallDistanceCycling(anyFloat());
        doNothing().when(user).setOverallDistanceRunning(anyFloat());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRoles(Mockito.<Collection<Role>>any());
        doNothing().when(user).setRunningList(Mockito.<List<Running>>any());
        doNothing().when(user).setUpdatedAt(Mockito.<Date>any());
        doNothing().when(user).setUser_id(Mockito.<UUID>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        doNothing().when(user).setWeight(anyFloat());
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = userService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(user).getPassword();
        verify(user).getUsername();
        verify(user).getRoles();
        verify(user).setBirthDate(Mockito.<Date>any());
        verify(user).setCreatedAt(Mockito.<Date>any());
        verify(user).setCyclingList(Mockito.<List<Cycling>>any());
        verify(user).setName(Mockito.<String>any());
        verify(user).setOverallDistanceCycling(anyFloat());
        verify(user).setOverallDistanceRunning(anyFloat());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRoles(Mockito.<Collection<Role>>any());
        verify(user).setRunningList(Mockito.<List<Running>>any());
        verify(user).setUpdatedAt(Mockito.<Date>any());
        verify(user).setUser_id(Mockito.<UUID>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(user).setWeight(anyFloat());
    }

    /**
     * Method under test: {@link UserService#saveUser(User)}
     */
    @Test
    void testSaveUser() {
        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        User user2 = new User();
        user2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setCyclingList(new ArrayList<>());
        user2.setName("Name");
        user2.setOverallDistanceCycling(10.0f);
        user2.setOverallDistanceRunning(10.0f);
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setRunningList(new ArrayList<>());
        user2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUser_id(UUID.randomUUID());
        user2.setUsername("janedoe");
        user2.setWeight(10.0f);
        assertSame(user, userService.saveUser(user2));
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertEquals("secret", user2.getPassword());
    }

    /**
     * Method under test: {@link UserService#saveUser(User)}
     */
    @Test
    void testSaveUser2() {
        when(passwordEncoder.encode(Mockito.<CharSequence>any()))
                .thenThrow(new UsernameNotFoundException("Saving new user {} to the database"));

        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        assertThrows(UsernameNotFoundException.class, () -> userService.saveUser(user));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserService#saveRole(Role)}
     */
    @Test
    void testSaveRole() {
        Role role = new Role();
        role.setName("Name");
        role.setRole_id(1L);
        when(roleRepository.save(Mockito.<Role>any())).thenReturn(role);

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRole_id(1L);
        assertSame(role, userService.saveRole(role2));
        verify(roleRepository).save(Mockito.<Role>any());
    }

    /**
     * Method under test: {@link UserService#saveRole(Role)}
     */
    @Test
    void testSaveRole2() {
        when(roleRepository.save(Mockito.<Role>any()))
                .thenThrow(new UsernameNotFoundException("Saving new role {} to the database"));

        Role role = new Role();
        role.setName("Name");
        role.setRole_id(1L);
        assertThrows(UsernameNotFoundException.class, () -> userService.saveRole(role));
        verify(roleRepository).save(Mockito.<Role>any());
    }

    /**
     * Method under test: {@link UserService#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser() {
        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);

        User user2 = new User();
        user2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setCyclingList(new ArrayList<>());
        user2.setName("Name");
        user2.setOverallDistanceCycling(10.0f);
        user2.setOverallDistanceRunning(10.0f);
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setRunningList(new ArrayList<>());
        user2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setUser_id(UUID.randomUUID());
        user2.setUsername("janedoe");
        user2.setWeight(10.0f);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);

        Role role = new Role();
        role.setName("Name");
        role.setRole_id(1L);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(role);
        userService.addRoleToUser("janedoe", "Role Name");
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(roleRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser2() {
        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        when(roleRepository.findByName(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("Adding role {} to user {}"));
        assertThrows(UsernameNotFoundException.class, () -> userService.addRoleToUser("janedoe", "Role Name"));
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(roleRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    void testGetUser() {
        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        assertSame(user, userService.getUser("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    void testGetUser2() {
        when(userRepository.findByUsername(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("Fetching user {}"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getUser("janedoe"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUsers()}
     */
    @Test
    void testGetUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualUsers = userService.getUsers();
        assertSame(userList, actualUsers);
        assertTrue(actualUsers.isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserService#getUsers()}
     */
    @Test
    void testGetUsers2() {
        when(userRepository.findAll()).thenThrow(new UsernameNotFoundException("Fetching all users"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserService#getUserByUsername(String)}
     */
    @Test
    void testGetUserByUsername() {
        User user = new User();
        user.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setCyclingList(new ArrayList<>());
        user.setName("Name");
        user.setOverallDistanceCycling(10.0f);
        user.setOverallDistanceRunning(10.0f);
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setRunningList(new ArrayList<>());
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setUser_id(UUID.randomUUID());
        user.setUsername("janedoe");
        user.setWeight(10.0f);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        assertSame(user, userService.getUserByUsername("Name"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUserByUsername(String)}
     */
    @Test
    void testGetUserByUsername2() {
        when(userRepository.findByUsername(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("Retrieving user with username {}"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByUsername("Name"));
        verify(userRepository).findByUsername(Mockito.<String>any());
    }
}

