package br.sc.senai.TrunfoFinalSpring.Controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.sc.senai.TrunfoFinalSpring.Model.DTO.PlayerDTO;
import br.sc.senai.TrunfoFinalSpring.Model.Entity.Player;
import br.sc.senai.TrunfoFinalSpring.Service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    public void testSearchAllPlayers() throws Exception {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setName("mary");
        Player player2 = new Player();
        player2.setName("mario");
        players.add(player1);
        players.add(player2);

        when(playerService.searchAll()).thenReturn(players);

        mockMvc.perform(get("/player/getPlayers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePlayer() throws Exception {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("mary");
        playerDTO.setPassword("password");

        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setPassword(playerDTO.getPassword());

        when(playerService.create(player)).thenReturn(player);

        mockMvc.perform(post("/player/crea te")
                .content(asJsonString(playerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}