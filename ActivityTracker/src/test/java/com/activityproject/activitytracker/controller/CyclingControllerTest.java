package com.activityproject.activitytracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.activityproject.activitytracker.dto.CyclingDto;
import com.activityproject.activitytracker.mapper.CyclingMapper;
import com.activityproject.activitytracker.mapper.CyclingMapperImpl;
import com.activityproject.activitytracker.model.Cycling;
import com.activityproject.activitytracker.model.User;
import com.activityproject.activitytracker.repository.CyclingRepository;
import com.activityproject.activitytracker.service.CyclingService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CyclingController.class})
@ExtendWith(SpringExtension.class)
class CyclingControllerTest {
    @Autowired
    private CyclingController cyclingController;

    @MockBean
    private CyclingService cyclingService;



    /**
     * Method under test: {@link CyclingController#createActivity(CyclingDto)}
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
        CyclingRepository cyclingRepository = mock(CyclingRepository.class);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenReturn(cycling);
        CyclingController cyclingController = new CyclingController(
                new CyclingService(new CyclingMapperImpl(), cyclingRepository));

        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        UUID id = UUID.randomUUID();
        cyclingDto.setId(id);
        cyclingDto.setSpeed(10.0f);
        ResponseEntity<CyclingDto> actualCreateActivityResult = cyclingController.createActivity(cyclingDto);
        CyclingDto body = actualCreateActivityResult.getBody();
        assertEquals(cyclingDto, body);
        assertTrue(actualCreateActivityResult.getHeaders().isEmpty());
        assertEquals(201, actualCreateActivityResult.getStatusCodeValue());
        assertEquals(10.0f, body.getAverageCadency());
        assertEquals(10.0f, body.getDistance());
        assertEquals("Z", body.getCreatedAt().getZone().toString());
        assertEquals(10.0f, body.getCalories());
        assertEquals(10.0f, body.getAveragePower());
        assertSame(id, body.getId());
        assertEquals(10.0f, body.getSpeed());
        assertEquals("Z", body.getDoneTimePoint().getZone().toString());
        assertNull(body.getDuration());
        verify(cyclingRepository).save(Mockito.<Cycling>any());
    }

    /**
     * Method under test: {@link CyclingController#createActivity(CyclingDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
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
        CyclingRepository cyclingRepository = mock(CyclingRepository.class);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenReturn(cycling);
        CyclingController cyclingController = new CyclingController(new CyclingService(null, cyclingRepository));

        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        cyclingController.createActivity(cyclingDto);
    }

    /**
     * Method under test: {@link CyclingController#createActivity(CyclingDto)}
     */
    @Test
    void testCreateActivity3() {
            CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        CyclingService cyclingService = mock(CyclingService.class);
        when(cyclingService.createActivity(Mockito.<CyclingDto>any())).thenReturn(cyclingDto);
        CyclingController cyclingController = new CyclingController(cyclingService);

        CyclingDto cyclingDto2 = new CyclingDto();
        cyclingDto2.setAverageCadency(10.0f);
        cyclingDto2.setAveragePower(10.0f);
        cyclingDto2.setCalories(10.0f);
        cyclingDto2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto2.setDistance(10.0f);
        cyclingDto2.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto2.setId(UUID.randomUUID());
        cyclingDto2.setSpeed(10.0f);
        ResponseEntity<CyclingDto> actualCreateActivityResult = cyclingController.createActivity(cyclingDto2);
        assertEquals(cyclingDto2, actualCreateActivityResult.getBody());
        assertTrue(actualCreateActivityResult.getHeaders().isEmpty());
        assertEquals(201, actualCreateActivityResult.getStatusCodeValue());
        verify(cyclingService).createActivity(Mockito.<CyclingDto>any());
    }

    /**
     * Method under test: {@link CyclingController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(cyclingService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cycling");
        MockMvcBuilders.standaloneSetup(cyclingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CyclingController#findById(UUID)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindById() {
         CyclingRepository cyclingRepository = mock(CyclingRepository.class);
        Optional<Cycling> emptyResult = Optional.empty();
        when(cyclingRepository.findById(Mockito.<UUID>any())).thenReturn(emptyResult);
        CyclingController cyclingController = new CyclingController(
                new CyclingService(new CyclingMapperImpl(), cyclingRepository));
        cyclingController.findById(UUID.randomUUID());
    }

    /**
     * Method under test: {@link CyclingController#findById(UUID)}
     */
    @Test
    void testFindById2() {
         CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        CyclingService cyclingService = mock(CyclingService.class);
        when(cyclingService.findById(Mockito.<UUID>any())).thenReturn(cyclingDto);
        CyclingController cyclingController = new CyclingController(cyclingService);
        assertSame(cyclingDto, cyclingController.findById(UUID.randomUUID()));
        verify(cyclingService).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link CyclingController#updateCycling(UUID, CyclingDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
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
        CyclingRepository cyclingRepository = mock(CyclingRepository.class);
        when(cyclingRepository.save(Mockito.<Cycling>any())).thenReturn(cycling);
        when(cyclingRepository.existsById(Mockito.<UUID>any())).thenReturn(true);
        CyclingController cyclingController = new CyclingController(new CyclingService(null, cyclingRepository));
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
        cyclingController.updateCycling(id, cyclingDto);
    }

    /**
     * Method under test: {@link CyclingController#updateCycling(UUID, CyclingDto)}
     */

    /**
     * Method under test: {@link CyclingController#updateCycling(UUID, CyclingDto)}
     */
    @Test
    void testUpdateCycling3() {
        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        CyclingService cyclingService = mock(CyclingService.class);
        when(cyclingService.updateCycling(Mockito.<UUID>any(), Mockito.<CyclingDto>any())).thenReturn(cyclingDto);
        CyclingController cyclingController = new CyclingController(cyclingService);
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
        assertSame(cyclingDto, cyclingController.updateCycling(id, cyclingDto2));
        verify(cyclingService).updateCycling(Mockito.<UUID>any(), Mockito.<CyclingDto>any());
    }

    /**
     * Method under test: {@link CyclingController#delete(UUID)}
     */
    @Test
    void testDelete() throws Exception {
        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        when(cyclingService.deleteCycling(Mockito.<UUID>any())).thenReturn(cyclingDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/cycling/{id}",
                UUID.randomUUID());
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cyclingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CyclingController#delete(UUID)}
     */
    @Test
    void testDelete2() throws Exception {
        CyclingDto cyclingDto = new CyclingDto();
        cyclingDto.setAverageCadency(10.0f);
        cyclingDto.setAveragePower(10.0f);
        cyclingDto.setCalories(10.0f);
        cyclingDto.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setDistance(10.0f);
        cyclingDto.setDoneTimePoint(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        cyclingDto.setId(UUID.randomUUID());
        cyclingDto.setSpeed(10.0f);
        when(cyclingService.deleteCycling(Mockito.<UUID>any())).thenReturn(cyclingDto);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cyclingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

