package com.example.spring.controller.c025;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class C025ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void uploadFormのGET() throws Exception {
		mockMvc.perform(get("/c025/uploadForm")).andExpect(status().isOk())
				.andExpect(view().name("c025/uploadForm"));
	}

	@Test
	public void uploadRecvのPOST() throws Exception {
		byte[] fileImage = null;
		Path path = Paths.get("src/test/resources/c025/kappa.png");
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			fileImage = Files.readAllBytes(path);
		}

		// ローカルのファイル名もエミュレーションできる。
		String fileName = "画像.png";
		MockMultipartFile file = new MockMultipartFile("file", fileName, null,
				fileImage);
		// アップロードされるファイルのパス
		Path actualFile = Paths.get(System.getProperty("java.io.tmpdir"),
				"画像.png");

		mockMvc.perform(
				fileUpload("/c025/uploadRecv").file(file).param("test",
						"testParam")).andExpect(status().isOk())
				.andExpect(view().name("c025/uploadRecv"))
				.andExpect(model().attribute("test", is("testParam")))
				.andExpect(model().attribute("fileName", actualFile));

		// 画像が保管されていることを確認する
		assertThat(Files.exists(actualFile, LinkOption.NOFOLLOW_LINKS),
				is(true));
		byte[] actualImage = Files.readAllBytes(actualFile);
		assertThat(actualImage, is(equalTo(fileImage)));
		// アップロードされたファイルの削除
		Files.delete(actualFile);
	}
}
