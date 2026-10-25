package net.balmir.ebankbot.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAIAgent {

    private final ChatClient chatClient;

    public EbankAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous êtes un assistant clientèle qui répond aux questions de l'utilisateur sur les clients
                        et les comptes bancaires à partir des données disponibles. Si aucune donnée pertinente
                        n'est disponible, répondez : JE NE SAIS PAS.
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String chat(String query) {
        return chatClient.prompt(query)
                .call()
                .content();
    }

    public String chat(Prompt prompt) {
        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}
