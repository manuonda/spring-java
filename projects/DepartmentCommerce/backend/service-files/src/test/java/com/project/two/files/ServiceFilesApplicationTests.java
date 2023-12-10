package com.project.two.files;

import com.project.two.files.business.service.FileService;
import com.project.two.files.presentation.advice.FileUploadException;
import com.project.two.files.presentation.controller.FileUploadController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FileUploadController.class)
@AutoConfigureWebMvc
class FileUploadControllerTest {

    @Autowired
   private MockMvc mockMvc;

  @MockBean
  private FileService fileService;


   @Test
	void contextLoads() {

    }

    @Test
    public void shouldUploadFile_returnObjectResultOk() throws Exception {
       //given
       String  fileName="fileText.txt";
       MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "fileText.txt",
                "application/octet-stream",
                "Hello World".getBytes()

        );
        //when
        when(fileService.uploadFile(any(MultipartFile.class))).thenReturn("numero_uno.txt");
        ResultActions resultActions = mockMvc.perform(multipart("/api/v1/files/upload")
                 .file(mockMultipartFile)
                 .contentType(MediaType.MULTIPART_FORM_DATA_VALUE));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.message").value("File upload successfully. File unique name: numero_uno.txt"));
        resultActions.andExpect(jsonPath("$.statusCode").value(200));
        resultActions.andExpect(result -> {
            Assertions.assertThat(jsonPath("$.message").value("File upload successfully. File unique name: null"));
        });
    }

    @Test
    public void shouldInvalidaFileUpload() throws Exception {
       //Mocking invalid file
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file","file.txt","application/octet-stream","Hello World".getBytes());

        when(fileService.uploadFile(mockMultipartFile)).thenReturn("");
        mockMvc.perform(multipart("/api/v1/files/upload")
                .file(mockMultipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isBadRequest());
   }

   @Test
   @DisplayName("Should download file")
    public void shouldDownloadFile_returnObjectText() throws Exception {
       // arrange
       String fileName = "fileText.txt";
       Object response = new Object();
       when(fileService.downloadFile(fileName)).thenReturn(response);

       // Act & Assert
       ResultActions resultActions = mockMvc.perform(get("/api/v1/files/download")
               .param("fileName", fileName))
               .andExpect(status().isOk())
               .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fileText.txt"));
               //.andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));

       verify(fileService).downloadFile(fileName);

    }
    @Test
    @DisplayName("Shoul No return file not found")
    public void shouldReturnNotFoundIfFileNotFound() throws Exception {
       // Arrange
        String fileName ="missing-file.txt";
        when(fileService.downloadFile(fileName)).thenReturn(null);
        //Act & Assert
        mockMvc.perform(get("/api/v1/files/download")
                .param("fileName", fileName))
                .andExpect(status().isNotFound());

        Mockito.verify(fileService).downloadFile(fileName);
    }

    @Test
    @DisplayName("should reject empty file name")
    void shouldRejectEmptyFileName() throws Exception {
       // Arrange
       String fileName = "";

       // Acct & Assert
        mockMvc.perform(get("/api/v1/files/download")
                .param("fileName", fileName))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("File not empty"))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath(".successful").value(false));

        verify(fileService, never()).downloadFile(fileName);
    }
}
