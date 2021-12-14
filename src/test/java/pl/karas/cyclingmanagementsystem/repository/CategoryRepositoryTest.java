package pl.karas.cyclingmanagementsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.Category;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void threeCategories_findCategoryByName_oneCategoryWithNameJunior () {
        //given
        Category zakCategory = Category.builder()
                            .name("ZAK").build();

        Category mlodzikCategory = Category.builder()
                .name("MLODZIK").build();

        Category juniorCategory = Category.builder()
                .name("JUNIOR").build();

        categoryRepository.saveAll(Set.of(zakCategory, mlodzikCategory, juniorCategory));

        //when
        Category category = this.categoryRepository.findCategoryByName("ZAK").get();


        //then
        assertThat(category.getName(), is("ZAK"));

    }

}