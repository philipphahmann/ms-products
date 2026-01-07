package br.com.postech.soat.bdd.steps;

import br.com.postech.soat.application.dto.CreateProductDto;
import br.com.postech.soat.application.usecases.CreateProductUseCase;
import br.com.postech.soat.domain.entity.Product;
import br.com.postech.soat.domain.enumtypes.Category;
import br.com.postech.soat.domain.valueobject.ProductCategory;
import br.com.postech.soat.domain.valueobject.ProductDescription;
import br.com.postech.soat.domain.valueobject.ProductImage;
import br.com.postech.soat.domain.valueobject.ProductName;
import br.com.postech.soat.domain.valueobject.ProductPrice;
import br.com.postech.soat.domain.valueobject.ProductSKU;
import br.com.postech.soat.openapi.model.PostProductsRequestDto;
import br.com.postech.soat.openapi.model.ProductCategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class CriarProdutoSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateProductUseCase createProductUseCase;

    private PostProductsRequestDto requestDto;
    private ResultActions resultActions;

    @Dado("que tenho os dados de um novo produto válido")
    public void que_tenho_os_dados_de_um_novo_produto_valido() {
        requestDto = new PostProductsRequestDto();
        requestDto.setSku("BDD-TEST-001");
        requestDto.setName("X-Burger BDD");
        requestDto.setPrice(25.50);
        requestDto.setDescription("Burger testado via BDD");
        requestDto.setImage("http://teste.com/img.jpg");
        requestDto.setCategory(ProductCategoryDto.SNACK);

        Product productMock = Product.create(
            new ProductSKU(requestDto.getSku()),
            new ProductName(requestDto.getName()),
            new ProductPrice(BigDecimal.valueOf(requestDto.getPrice())),
            new ProductDescription(requestDto.getDescription()),
            new ProductImage(requestDto.getImage()),
            new ProductCategory(requestDto.getCategory().getValue())
        );

        when(createProductUseCase.execute(any(CreateProductDto.class)))
            .thenReturn(productMock);
    }

    @Quando("eu enviar uma requisição para criar o produto")
    public void eu_enviar_uma_requisicao_para_criar_o_produto() throws Exception {
        resultActions = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));
    }

    @Entao("o produto deve ser criado com sucesso")
    public void o_produto_deve_ser_criado_com_sucesso() throws Exception {
        resultActions
         .andExpect(jsonPath("$.sku").value("BDD-TEST-001"))
         .andExpect(jsonPath("$.name").value("X-Burger BDD"));
    }

    @Entao("o status da resposta deve ser {int} Created")
    public void o_status_da_resposta_deve_ser_created(int status) throws Exception {
        resultActions.andExpect(status().is(status));
    }
}