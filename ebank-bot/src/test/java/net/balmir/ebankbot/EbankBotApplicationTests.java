package net.balmir.ebankbot;

import net.balmir.ebankbot.agent.EbankAIAgent;
import net.balmir.ebankbot.controllers.EbankChatbotController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EbankChatbotController.class)
class EbankBotApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EbankAIAgent ebankAIAgent;

    @Test
    void chatEndpointReturnsAgentResponse() throws Exception {
        given(ebankAIAgent.chat("Bonjour")).willReturn("Bonjour !");

        mockMvc.perform(get("/chat").param("query", "Bonjour"))
                .andExpect(status().isOk())
                .andExpect(content().string("Bonjour !"));
    }
}
