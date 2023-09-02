package com.activityproject.activitytracker.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.activityproject.activitytracker.dto.CyclingDto;
import com.activityproject.activitytracker.mapper.CyclingMapper;
import com.activityproject.activitytracker.model.Cycling;
import com.activityproject.activitytracker.model.User;
import com.activityproject.activitytracker.repository.CyclingRepository;
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

@ContextConfiguration(classes = {CyclingService.class})
@ExtendWith(SpringExtension.class)
class CyclingServiceTest {
    @MockBean
    private CyclingMapper cyclingMapper;

    @MockBean
    private CyclingRepository cyclingRepository;

    @Autowired
    private CyclingService cyclingService;

    /**
     * Method under test: {@link CyclingService#createActivity(CyclingDto)}
     */
    @Test
    void testCreateActivity() {
        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);

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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        when(cyclingMapper.toDto(Mockito.<Cycling>any())).thenReturn(cyclingDto);
        when(cyclingMapper.toEntity(Mockito.<CyclingDto>any())).thenReturn(cycling);

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

        Cycling cycling2 = new Cycling();
        cycling2.setAverageCadency(10.0f);
        cycling2.setAveragePower(10.0f);
        cycling2.setCalories(10.0f);
        cycling2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling2.setDistance(10.0f);
        cycling2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling2.setId(UUID.randomUUID());
        cycling2.setSpeed(10.0f);
        cycling2.setUser(user2);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenReturn(cycling2);

        CyclingDto cyclingDto2 = new CyclingDto();
        cyclingDto2.setAverageCadency(10.0f);
        cyclingDto2.setAveragePower(10.0f);
        cyclingDto2.setCalories(10.0f);
        cyclingDto2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto2.setDistance(10.0f);
        cyclingDto2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto2.setId(UUID.randomUUID());
        cyclingDto2.setSpeed(10.0f);
        assertSame(cyclingDto, cyclingService.createActivity(cyclingDto2));
        verify(cyclingMapper).toDto(Mockito.<Cycling>any());
        verify(cyclingMapper).toEntity(Mockito.<CyclingDto>any());
        verify(cyclingRepository).save(Mockito.<Cycling>any());
    }

    /**
     * Method under test: {@link CyclingService#createActivity(CyclingDto)}
     */
    @Test
    void testCreateActivity2() {
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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        when(cyclingMapper.toEntity(Mockito.<CyclingDto>any())).thenReturn(cycling);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenThrow(new EntityNotFoundException("An error occurred"));

        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        assertThrows(EntityNotFoundException.class, () -> cyclingService.createActivity(cyclingDto));
        verify(cyclingMapper).toEntity(Mockito.<CyclingDto>any());
        verify(cyclingRepository).save(Mockito.<Cycling>any());
    }

    /**
     * Method under test: {@link CyclingService#findAll()}
     */
    @Test
    void testFindAll() {
        when(cyclingRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(cyclingService.findAll().isEmpty());
        verify(cyclingRepository).findAll();
    }

    /**
     * Method under test: {@link CyclingService#findAll()}
     */


    /**
     * Method under test: {@link CyclingService#deleteCycling(UUID)}
     */
    @Test
    void testDeleteCycling() {
        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        when(cyclingMapper.toDto(Mockito.<Cycling>any())).thenReturn(cyclingDto);

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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        Optional<Cycling> ofResult = Optional.of(cycling);
        doNothing().when(cyclingRepository).deleteById(Mockito.<UUID>any());
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        assertSame(cyclingDto, cyclingService.deleteCycling(UUID.randomUUID()));
        verify(cyclingMapper).toDto(Mockito.<Cycling>any());
        verify(cyclingRepository).findById(Mockito.<UUID>any());
        verify(cyclingRepository).deleteById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link CyclingService#deleteCycling(UUID)}
     */
    @Test
    void testDeleteCycling2() {
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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        Optional<Cycling> ofResult = Optional.of(cycling);
        doThrow(new EntityNotFoundException("An error occurred")).when(cyclingRepository).deleteById(Mockito.<UUID>any());
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> cyclingService.deleteCycling(UUID.randomUUID()));
        verify(cyclingRepository).findById(Mockito.<UUID>any());
        verify(cyclingRepository).deleteById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link CyclingService#deleteCycling(UUID)}
     */
    @Test
    void testDeleteCycling3() {
        Optional<Cycling> emptyResult = Optional.empty();
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(emptyResult);
        assertThrows(ResponseStatusException.class, () -> cyclingService.deleteCycling(UUID.randomUUID()));
        verify(cyclingRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link CyclingService#updateCycling(UUID, CyclingDto)}
     */
    @Test
    void testUpdateCycling() {
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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);

        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        when(cyclingMapper.toDto(Mockito.<Cycling>any())).thenReturn(cyclingDto);
        when(cyclingMapper.toEntity(Mockito.<CyclingDto>any())).thenReturn(cycling);

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

        Cycling cycling2 = new Cycling();
        cycling2.setAverageCadency(10.0f);
        cycling2.setAveragePower(10.0f);
        cycling2.setCalories(10.0f);
        cycling2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling2.setDistance(10.0f);
        cycling2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling2.setId(UUID.randomUUID());
        cycling2.setSpeed(10.0f);
        cycling2.setUser(user2);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenReturn(cycling2);
        when(cyclingRepository.existsById(Mockito.<UUID>any())).thenReturn(true);
        UUID id = UUID.randomUUID();

        CyclingDto cyclingDto2 = new CyclingDto();
        cyclingDto2.setAverageCadency(10.0f);
        cyclingDto2.setAveragePower(10.0f);
        cyclingDto2.setCalories(10.0f);
        cyclingDto2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto2.setDistance(10.0f);
        cyclingDto2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto2.setId(UUID.randomUUID());
        cyclingDto2.setSpeed(10.0f);
        assertSame(cyclingDto, cyclingService.updateCycling(id, cyclingDto2));
        verify(cyclingMapper).toDto(Mockito.<Cycling>any());
        verify(cyclingMapper).toEntity(Mockito.<CyclingDto>any());
        verify(cyclingRepository).existsById(Mockito.<UUID>any());
        verify(cyclingRepository).save(Mockito.<Cycling>any());
    }

    /**
     * Method under test: {@link CyclingService#updateCycling(UUID, CyclingDto)}
     */
    @Test
    void testUpdateCycling2() {
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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        when(cyclingMapper.toEntity(Mockito.<CyclingDto>any())).thenReturn(cycling);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(cyclingRepository.existsById(Mockito.<UUID>any())).thenReturn(true);
        UUID id = UUID.randomUUID();

        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        assertThrows(EntityNotFoundException.class, () -> cyclingService.updateCycling(id, cyclingDto));
        verify(cyclingMapper).toEntity(Mockito.<CyclingDto>any());
        verify(cyclingRepository).existsById(Mockito.<UUID>any());
        verify(cyclingRepository).save(Mockito.<Cycling>any());
    }

    /**
     * Method under test: {@link CyclingService#findById(UUID)}
     */
    @Test
    void testFindById() {
        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        when(cyclingMapper.toDto(Mockito.<Cycling>any())).thenReturn(cyclingDto);

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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        Optional<Cycling> ofResult = Optional.of(cycling);
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        assertSame(cyclingDto, cyclingService.findById(UUID.randomUUID()));
        verify(cyclingMapper).toDto(Mockito.<Cycling>any());
        verify(cyclingRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link CyclingService#findById(UUID)}
     */
    @Test
    void testFindById2() {
        when(cyclingMapper.toDto(Mockito.<Cycling>any())).thenThrow(new EntityNotFoundException("An error occurred"));

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

        Cycling cycling = new Cycling();
        cycling.setAverageCadency(10.0f);
        cycling.setAveragePower(10.0f);
        cycling.setCalories(10.0f);
        cycling.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setDistance(10.0f);
        cycling.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cycling.setId(UUID.randomUUID());
        cycling.setSpeed(10.0f);
        cycling.setUser(user);
        Optional<Cycling> ofResult = Optional.of(cycling);
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> cyclingService.findById(UUID.randomUUID()));
        verify(cyclingMapper).toDto(Mockito.<Cycling>any());
        verify(cyclingRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link CyclingService#findById(UUID)}
     */
    @Test
    void testFindById3() {
        Optional<Cycling> emptyResult = Optional.empty();
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(emptyResult);
        assertThrows(ResponseStatusException.class, () -> cyclingService.findById(UUID.randomUUID()));
        verify(cyclingRepository).findById(Mockito.<UUID>any());
    }
}

