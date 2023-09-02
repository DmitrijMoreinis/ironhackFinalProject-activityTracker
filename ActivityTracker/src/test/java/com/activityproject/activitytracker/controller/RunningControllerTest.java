package com.activityproject.activitytracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.activityproject.activitytracker.dto.RunningDto;
import com.activityproject.activitytracker.mapper.RunningMapperImpl;
import com.activityproject.activitytracker.model.Running;
import com.activityproject.activitytracker.model.User;
import com.activityproject.activitytracker.repository.RunningRepository;
import com.activityproject.activitytracker.service.RunningService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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

@ContextConfiguration(classes = {RunningController.class})
@ExtendWith(SpringExtension.class)
class RunningControllerTest {
    @Autowired
    private RunningController runningController;

    @MockBean
    private RunningService runningService;

    /**
     * Method under test: {@link RunningController#createActivity(RunningDto)}
     */
    @Test
    void testCreateActivity() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.ZonedDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.activityproject.activitytracker.dto.RunningDto["createdAt"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

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
        RunningRepository runningRepository = mock(RunningRepository.class);
        when(runningRepository.save(Mockito.<Running>any())).thenReturn(running);
        RunningController runningController = new RunningController(
                new RunningService(runningRepository, new RunningMapperImpl()));
        ResponseEntity<RunningDto> actualCreateActivityResult = runningController.createActivity(new RunningDto());
        assertTrue(actualCreateActivityResult.hasBody());
        assertTrue(actualCreateActivityResult.getHeaders().isEmpty());
        assertEquals(201, actualCreateActivityResult.getStatusCodeValue());
        RunningDto body = actualCreateActivityResult.getBody();
        assertEquals(0.0f, body.getSpeed());
        assertEquals(0.0f, body.getDistance());
        assertNull(body.getCreatedAt());
        assertEquals(0.0f, body.getCalories());
        assertNull(body.getId());
        assertNull(body.getDuration());
        assertNull(body.getDoneTimePoint());
        verify(runningRepository).save(Mockito.<Running>any());
    }


    /**
     * Method under test: {@link RunningController#createActivity(RunningDto)}
     */
    @Test
    void testCreateActivity3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.ZonedDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.activityproject.activitytracker.dto.RunningDto["createdAt"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        RunningService runningService = mock(RunningService.class);
        when(runningService.createActivity(Mockito.<RunningDto>any())).thenReturn(new RunningDto());
        RunningController runningController = new RunningController(runningService);
        ResponseEntity<RunningDto> actualCreateActivityResult = runningController.createActivity(new RunningDto());
        assertTrue(actualCreateActivityResult.hasBody());
        assertTrue(actualCreateActivityResult.getHeaders().isEmpty());
        assertEquals(201, actualCreateActivityResult.getStatusCodeValue());
        verify(runningService).createActivity(Mockito.<RunningDto>any());
    }

    /**
     * Method under test: {@link RunningController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(runningService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/running");
        MockMvcBuilders.standaloneSetup(runningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RunningController#findById(UUID)}
     */
    @Test
    void testFindById() throws Exception {
        when(runningService.findById(Mockito.<UUID>any())).thenReturn(new RunningDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/running/{id}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(runningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"distance\":0.0,\"duration\":null,\"speed\":0.0,\"calories\":0.0,\"createdAt\":null,\"doneTimePoint"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link RunningController#findById(UUID)}
     */
    @Test
    void testFindById2() throws Exception {
        when(runningService.findAll()).thenReturn(new ArrayList<>());
        when(runningService.findById(Mockito.<UUID>any())).thenReturn(new RunningDto());
        UUID.randomUUID();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/running/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(runningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RunningController#updateRunning(UUID, RunningDto)}
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
        RunningRepository runningRepository = mock(RunningRepository.class);
        when(runningRepository.save(Mockito.<Running>any())).thenReturn(running);
        when(runningRepository.existsById(Mockito.<UUID>any())).thenReturn(true);
        RunningController runningController = new RunningController(
                new RunningService(runningRepository, new RunningMapperImpl()));
        UUID id = UUID.randomUUID();
        RunningDto actualUpdateRunningResult = runningController.updateRunning(id, new RunningDto());
        assertEquals(0.0f, actualUpdateRunningResult.getCalories());
        assertEquals(0.0f, actualUpdateRunningResult.getSpeed());
        assertSame(id, actualUpdateRunningResult.getId());
        assertNull(actualUpdateRunningResult.getDuration());
        assertNull(actualUpdateRunningResult.getCreatedAt());
        assertNull(actualUpdateRunningResult.getDoneTimePoint());
        assertEquals(0.0f, actualUpdateRunningResult.getDistance());
        verify(runningRepository).existsById(Mockito.<UUID>any());
        verify(runningRepository).save(Mockito.<Running>any());
    }

    /**
     * Method under test: {@link RunningController#updateRunning(UUID, RunningDto)}
     */

    @Test
    void testUpdateRunning4() {
               RunningService runningService = mock(RunningService.class);
        RunningDto runningDto = new RunningDto();
        when(runningService.updateRunning(Mockito.<UUID>any(), Mockito.<RunningDto>any())).thenReturn(runningDto);
        RunningController runningController = new RunningController(runningService);
        UUID id = UUID.randomUUID();
        assertSame(runningDto, runningController.updateRunning(id, new RunningDto()));
        verify(runningService).updateRunning(Mockito.<UUID>any(), Mockito.<RunningDto>any());
    }

    /**
     * Method under test: {@link RunningController#delete(UUID)}
     */
    @Test
    void testDelete() throws Exception {
        when(runningService.deleteRunning(Mockito.<UUID>any())).thenReturn(new RunningDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/running/{id}",
                UUID.randomUUID());
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(runningController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link RunningController#delete(UUID)}
     */
    @Test
    void testDelete2() throws Exception {
        when(runningService.deleteRunning(Mockito.<UUID>any())).thenReturn(new RunningDto());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(runningController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

