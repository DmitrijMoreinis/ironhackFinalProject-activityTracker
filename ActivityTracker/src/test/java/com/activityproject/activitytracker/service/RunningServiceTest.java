package com.activityproject.activitytracker.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.activityproject.activitytracker.dto.RunningDto;
import com.activityproject.activitytracker.mapper.RunningMapper;
import com.activityproject.activitytracker.model.Running;
import com.activityproject.activitytracker.model.User;
import com.activityproject.activitytracker.repository.RunningRepository;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {RunningService.class})
@ExtendWith(SpringExtension.class)
class RunningServiceTest {
    @MockBean
    private RunningMapper runningMapper;

    @MockBean
    private RunningRepository runningRepository;

    @Autowired
    private RunningService runningService;

    /**
     * Method under test: {@link RunningService#createActivity(RunningDto)}
     */
    @Test
    void testCreateActivity() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        when(runningRepository.save(Mockito.<Running>any())).thenReturn(running);

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

        Running running2 = new Running();
        running2.setCalories(10.0f);
        running2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running2.setDistance(10.0f);
        running2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running2.setId(UUID.randomUUID());
        running2.setSpeed(10.0f);
        running2.setSprints(1);
        running2.setUser(user2);
        RunningDto runningDto = new RunningDto();
        when(runningMapper.toDto(Mockito.<Running>any())).thenReturn(runningDto);
        when(runningMapper.toEntity(Mockito.<RunningDto>any())).thenReturn(running2);
        assertSame(runningDto, runningService.createActivity(new RunningDto()));
        verify(runningRepository).save(Mockito.<Running>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
        verify(runningMapper).toEntity(Mockito.<RunningDto>any());
    }

    /**
     * Method under test: {@link RunningService#findAll()}
     */
    @Test
    void testFindAll() {
        when(runningRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(runningService.findAll().isEmpty());
        verify(runningRepository).findAll();
    }

    /**
     * Method under test: {@link RunningService#findById(UUID)}
     */
    @Test
    void testFindById() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        Optional<Running> ofResult = Optional.of(running);
        when(runningRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        RunningDto runningDto = new RunningDto();
        when(runningMapper.toDto(Mockito.<Running>any())).thenReturn(runningDto);
        assertSame(runningDto, runningService.findById(UUID.randomUUID()));
        verify(runningRepository).findById(Mockito.<UUID>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
    }

    /**
     * Method under test: {@link RunningService#findById(UUID)}
     */
    @Test
    void testFindById2() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        Optional<Running> ofResult = Optional.of(running);
        when(runningRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(runningMapper.toDto(Mockito.<Running>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> runningService.findById(UUID.randomUUID()));
        verify(runningRepository).findById(Mockito.<UUID>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
    }

    /**
     * Method under test: {@link RunningService#findById(UUID)}
     */
    @Test
    void testFindById3() {
        Optional<Running> emptyResult = Optional.empty();
        when(runningRepository.findById(Mockito.<UUID>any())).thenReturn(emptyResult);
        assertThrows(ResponseStatusException.class, () -> runningService.findById(UUID.randomUUID()));
        verify(runningRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link RunningService#updateRunning(UUID, RunningDto)}
     */
    @Test
    void testUpdateRunning() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        when(runningRepository.save(Mockito.<Running>any())).thenReturn(running);
        when(runningRepository.existsById(Mockito.<UUID>any())).thenReturn(true);

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

        Running running2 = new Running();
        running2.setCalories(10.0f);
        running2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running2.setDistance(10.0f);
        running2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running2.setId(UUID.randomUUID());
        running2.setSpeed(10.0f);
        running2.setSprints(1);
        running2.setUser(user2);
        RunningDto runningDto = new RunningDto();
        when(runningMapper.toDto(Mockito.<Running>any())).thenReturn(runningDto);
        when(runningMapper.toEntity(Mockito.<RunningDto>any())).thenReturn(running2);
        UUID id = UUID.randomUUID();
        assertSame(runningDto, runningService.updateRunning(id, new RunningDto()));
        verify(runningRepository).existsById(Mockito.<UUID>any());
        verify(runningRepository).save(Mockito.<Running>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
        verify(runningMapper).toEntity(Mockito.<RunningDto>any());
    }

    /**
     * Method under test: {@link RunningService#updateRunning(UUID, RunningDto)}
     */
    @Test
    void testUpdateRunning2() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        when(runningRepository.save(Mockito.<Running>any())).thenReturn(running);
        when(runningRepository.existsById(Mockito.<UUID>any())).thenReturn(true);

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

        Running running2 = new Running();
        running2.setCalories(10.0f);
        running2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running2.setDistance(10.0f);
        running2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running2.setId(UUID.randomUUID());
        running2.setSpeed(10.0f);
        running2.setSprints(1);
        running2.setUser(user2);
        when(runningMapper.toDto(Mockito.<Running>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(runningMapper.toEntity(Mockito.<RunningDto>any())).thenReturn(running2);
        UUID id = UUID.randomUUID();
        assertThrows(EntityNotFoundException.class, () -> runningService.updateRunning(id, new RunningDto()));
        verify(runningRepository).existsById(Mockito.<UUID>any());
        verify(runningRepository).save(Mockito.<Running>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
        verify(runningMapper).toEntity(Mockito.<RunningDto>any());
    }

    /**
     * Method under test: {@link RunningService#updateRunning(UUID, RunningDto)}
     */
    @Test
    void testUpdateRunning3() {
        when(runningRepository.existsById(Mockito.<UUID>any())).thenReturn(false);
        UUID id = UUID.randomUUID();
        assertThrows(EntityNotFoundException.class, () -> runningService.updateRunning(id, new RunningDto()));
        verify(runningRepository).existsById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link RunningService#deleteRunning(UUID)}
     */
    @Test
    void testDeleteRunning() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        Optional<Running> ofResult = Optional.of(running);
        doNothing().when(runningRepository).deleteById(Mockito.<UUID>any());
        when(runningRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        RunningDto runningDto = new RunningDto();
        when(runningMapper.toDto(Mockito.<Running>any())).thenReturn(runningDto);
        assertSame(runningDto, runningService.deleteRunning(UUID.randomUUID()));
        verify(runningRepository).findById(Mockito.<UUID>any());
        verify(runningRepository).deleteById(Mockito.<UUID>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
    }

    /**
     * Method under test: {@link RunningService#deleteRunning(UUID)}
     */
    @Test
    void testDeleteRunning2() {
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

        Running running = new Running();
        running.setCalories(10.0f);
        running.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setDistance(10.0f);
        running.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        running.setId(UUID.randomUUID());
        running.setSpeed(10.0f);
        running.setSprints(1);
        running.setUser(user);
        Optional<Running> ofResult = Optional.of(running);
        doNothing().when(runningRepository).deleteById(Mockito.<UUID>any());
        when(runningRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(runningMapper.toDto(Mockito.<Running>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> runningService.deleteRunning(UUID.randomUUID()));
        verify(runningRepository).findById(Mockito.<UUID>any());
        verify(runningRepository).deleteById(Mockito.<UUID>any());
        verify(runningMapper).toDto(Mockito.<Running>any());
    }

    /**
     * Method under test: {@link RunningService#deleteRunning(UUID)}
     */
    @Test
    void testDeleteRunning3() {
        Optional<Running> emptyResult = Optional.empty();
        when(runningRepository.findById(Mockito.<UUID>any())).thenReturn(emptyResult);
        assertThrows(ResponseStatusException.class, () -> runningService.deleteRunning(UUID.randomUUID()));
        verify(runningRepository).findById(Mockito.<UUID>any());
    }
}

