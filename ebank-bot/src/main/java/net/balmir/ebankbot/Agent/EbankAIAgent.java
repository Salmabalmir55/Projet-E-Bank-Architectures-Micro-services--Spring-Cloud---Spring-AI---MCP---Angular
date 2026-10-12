package net.balmir.ebankbot.Agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EbankAIAgent {


private final ChatClient chatClient;

public EbankAIAgent(ChatClient.Builder chatClient,
                    ChatMemory chatMemory , ToolCallbackProvider tools) {
    this.chatClient = chatClient
            .defaultSystem("""
                            
                    "vous êtes un assistant clientèle qui se charge de répondre aux questions
                    de l'utilisateur à propos des clients et des comptes bancaires , en fonction du contexte fourni.
                    si aucun contexte n'est fourni , répond avec JE NE SAIS PAS  
                    """)

            .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
            .defaultTools(tools)
            .build();
}

@GetMapping("/chat")
public String chat(@RequestParam(name = "query", defaultValue = "Bonjour") String query) {
    return chatClient.prompt(query)
            .call()
            .content();
}
}
