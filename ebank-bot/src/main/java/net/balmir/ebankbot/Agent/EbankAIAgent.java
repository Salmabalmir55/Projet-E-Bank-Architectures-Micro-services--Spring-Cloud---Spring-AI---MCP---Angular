package net.balmir.ebankbot.Agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EbankAIAgent {


private ChatClient chatClient;

public EbankAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory) {
    this.chatClient = chatClient
            .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
            .build();
}

@GetMapping("/chat")
public String chat(@RequestParam(name = "query", defaultValue = "Bonjour") String query) {
    return chatClient.prompt(query)
            .call()
            .content();
}
}
