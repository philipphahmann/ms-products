package br.com.postech.soat.product.infrastructure.http;

import br.com.postech.soat.openapi.model.GetProduct200ResponseInnerDto;
import br.com.postech.soat.openapi.model.PostProducts201ResponseDto;
import br.com.postech.soat.openapi.model.PostProductsRequestDto;
import br.com.postech.soat.openapi.model.ProductCategoryDto;
import br.com.postech.soat.openapi.model.ProductDto;
import br.com.postech.soat.openapi.model.PutProductsRequestDto;
import br.com.postech.soat.product.application.dto.CreateProductDto;
import br.com.postech.soat.product.application.dto.UpdateProductDto;
import br.com.postech.soat.product.domain.entity.Product;
import br.com.postech.soat.product.domain.entity.ProductId;
import br.com.postech.soat.product.domain.valueobject.ProductCategory;
import br.com.postech.soat.product.domain.valueobject.ProductDescription;
import br.com.postech.soat.product.domain.valueobject.ProductImage;
import br.com.postech.soat.product.domain.valueobject.ProductName;
import br.com.postech.soat.product.domain.valueobject.ProductPrice;
import br.com.postech.soat.product.domain.valueobject.ProductSKU;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-26T09:59:19-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ProductWebMapperImpl implements ProductWebMapper {

    @Override
    public CreateProductDto toCreateRequest(PostProductsRequestDto postProductsRequestDto) {
        if ( postProductsRequestDto == null ) {
            return null;
        }

        String sku = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        String image = null;
        String category = null;

        sku = postProductsRequestDto.getSku();
        name = postProductsRequestDto.getName();
        description = postProductsRequestDto.getDescription();
        if ( postProductsRequestDto.getPrice() != null ) {
            price = BigDecimal.valueOf( postProductsRequestDto.getPrice() );
        }
        image = postProductsRequestDto.getImage();
        if ( postProductsRequestDto.getCategory() != null ) {
            category = postProductsRequestDto.getCategory().name();
        }

        CreateProductDto createProductDto = new CreateProductDto( sku, name, description, price, image, category );

        return createProductDto;
    }

    @Override
    public UpdateProductDto toUpdateRequest(PutProductsRequestDto putProductRequest) {
        if ( putProductRequest == null ) {
            return null;
        }

        String name = null;
        BigDecimal price = null;
        String description = null;
        String image = null;
        String category = null;

        name = putProductRequest.getName();
        if ( putProductRequest.getPrice() != null ) {
            price = BigDecimal.valueOf( putProductRequest.getPrice() );
        }
        description = putProductRequest.getDescription();
        image = putProductRequest.getImage();
        if ( putProductRequest.getCategory() != null ) {
            category = putProductRequest.getCategory().name();
        }

        UpdateProductDto updateProductDto = new UpdateProductDto( name, price, description, image, category );

        return updateProductDto;
    }

    @Override
    public PostProducts201ResponseDto toCreateResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        PostProducts201ResponseDto.Builder postProducts201ResponseDto = PostProducts201ResponseDto.builder();

        UUID value = productIdValue( product );
        if ( value != null ) {
            postProducts201ResponseDto.id( value.toString() );
        }

        return postProducts201ResponseDto.build();
    }

    @Override
    public GetProduct200ResponseInnerDto toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        GetProduct200ResponseInnerDto.Builder getProduct200ResponseInnerDto = GetProduct200ResponseInnerDto.builder();

        getProduct200ResponseInnerDto.sku( productSkuValue( product ) );
        getProduct200ResponseInnerDto.active( product.getActive() );
        getProduct200ResponseInnerDto.name( productNameValue( product ) );
        BigDecimal value2 = productPriceValue( product );
        if ( value2 != null ) {
            getProduct200ResponseInnerDto.price( value2.doubleValue() );
        }
        getProduct200ResponseInnerDto.description( productDescriptionValue( product ) );
        getProduct200ResponseInnerDto.image( productImageValue( product ) );
        String value5 = productCategoryValue( product );
        if ( value5 != null ) {
            getProduct200ResponseInnerDto.category( Enum.valueOf( ProductCategoryDto.class, value5 ) );
        }

        getProduct200ResponseInnerDto.id( ProductId.of(product.getId().getValue()).getValue() );

        return getProduct200ResponseInnerDto.build();
    }

    @Override
    public ProductDto toUpdateResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.Builder productDto = ProductDto.builder();

        productDto.sku( productSkuValue( product ) );
        productDto.name( productNameValue( product ) );
        productDto.description( productDescriptionValue( product ) );
        BigDecimal value3 = productPriceValue( product );
        if ( value3 != null ) {
            productDto.price( value3.doubleValue() );
        }
        productDto.active( product.getActive() );
        productDto.image( productImageValue( product ) );
        String value5 = productCategoryValue( product );
        if ( value5 != null ) {
            productDto.category( Enum.valueOf( ProductCategoryDto.class, value5 ) );
        }

        productDto.id( ProductId.of(product.getId().getValue()).getValue() );

        return productDto.build();
    }

    @Override
    public List<GetProduct200ResponseInnerDto> toListResponse(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<GetProduct200ResponseInnerDto> list = new ArrayList<GetProduct200ResponseInnerDto>( products.size() );
        for ( Product product : products ) {
            list.add( toResponse( product ) );
        }

        return list;
    }

    private UUID productIdValue(Product product) {
        ProductId id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id.getValue();
    }

    private String productSkuValue(Product product) {
        ProductSKU sku = product.getSku();
        if ( sku == null ) {
            return null;
        }
        return sku.value();
    }

    private String productNameValue(Product product) {
        ProductName name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name.value();
    }

    private BigDecimal productPriceValue(Product product) {
        ProductPrice price = product.getPrice();
        if ( price == null ) {
            return null;
        }
        return price.value();
    }

    private String productDescriptionValue(Product product) {
        ProductDescription description = product.getDescription();
        if ( description == null ) {
            return null;
        }
        return description.value();
    }

    private String productImageValue(Product product) {
        ProductImage image = product.getImage();
        if ( image == null ) {
            return null;
        }
        return image.value();
    }

    private String productCategoryValue(Product product) {
        ProductCategory category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.value();
    }
}
