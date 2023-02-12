package com.member.api.integrated;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql("classpath:member-test-data.sql")
public class MemberIntegratedTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInfoTest() throws Exception {
        mockMvc.perform(get("/api/v1/members/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
