package ru.internetshop.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.PersonalComputerRepository;
import ru.internetshop.service.impl.PersonalComputerServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PersonalComputersControllerTests {

    @Mock
    PersonalComputersController personalComputersController;

    @Mock
    PersonalComputerServiceImpl computerService;

    @Mock
    PersonalComputerRepository repository;


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    RestTemplate restTemplate = new RestTemplate();

    @Mock
    PersonalComputer personalComputer = new PersonalComputer("SER01-001", "Intel", 10000, 1, "PC");

//    @Test
//    public void testAddPersonalComputer() throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Mockito.when(personalComputersController.create(personalComputer)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
//
//        this.mockMvc.perform(post("/PersonalComputers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"serialNumber\" :\"12332-332\",\n" +
//                                "    \"company\" : \"Update\",\n" +
//                                "    \"price\" : 10000,\n" +
//                                "    \"quantity\":1,\n" +
//                                "    \"formFactor\":\"PC\""))
//                .andExpect(status().isCreated());
//
//    }
}
