package com.alkma.ketabee.book;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookService bookService;

    @Test
    void shouldCreateBook() throws Exception {
        BookResponse response = BookResponse.builder().id(1L).title("The Great Gatsby").author("F. Scott Fitzgerald")
                .isbn("9780743273565").price(10.99).build();

        when(bookService.createBook(any())).thenReturn(response);

        BookRequest request = BookRequest.builder().title("The Great Gatsby").author("F. Scott Fitzgerald")
                .isbn("9780743273565").price(10.99).description("A story of the American Dream").publisher("Scribner")
                .publicationDate("1925-04-10").category("Fiction").language("English").imageUrl(null)
                .status("AVAILABLE").build();

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.title").value("The Great Gatsby"))
                .andExpect(jsonPath("$.author").value("F. Scott Fitzgerald"))
                .andExpect(jsonPath("$.isbn").value("9780743273565")).andExpect(jsonPath("$.price").value(10.99));
    }

    @Test
    void shouldReturn400WhenTitleIsNull() throws Exception {
        String invalidJson = "{\"author\":\"Test\"}";

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}