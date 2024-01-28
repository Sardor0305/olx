package uz.pdp.olx.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import uz.pdp.olx.enam.ItemCondition;
import uz.pdp.olx.enitiy.Category;
import uz.pdp.olx.enitiy.Product;
import uz.pdp.olx.enitiy.User;
import uz.pdp.olx.repository.CategoryRepository;
import uz.pdp.olx.repository.ProductRepository;
import uz.pdp.olx.repository.UserRepository;
@RequiredArgsConstructor
@Configuration
public class CustomCommonLiner implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddl;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if(ddl.equals("create")) {

            User user1 = User.builder()
                    .email("emali@gmail.com")
                    .password("12345")
                    .username("username1")
                    .build();
            User user2 = User.builder()
                    .email("gmali@gmail.com")
                    .password("12345")
                    .username("username2")
                    .build();
            User save1 = userRepository.save(user1);
            User save = userRepository.save(user2);

            Category category3 = Category.builder().name("category").build();
            Category category2 = Category.builder().name("category").build();
            Category category1 = Category.builder().name("category").build();
            Category category = Category.builder().name("category").build();
            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);

            Product product = Product.builder()
                    .title("title")
                    .description("description")
                    .user(save)
                    .price(100.0).category(category).itemCondition(ItemCondition.NEW).ratingValue(1.0F).build();
            Product product1 = Product.builder()
                    .title("title1")
                    .description("description1")
                    .user(save1)
                    .price(100.0).category(category1).itemCondition(ItemCondition.NEW).ratingValue(1.0F).build();


            productRepository.save(product);
            productRepository.save(product1);

        }

    }
}
