package com.example.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @Filter(EnableJpaAuditing.class))
@ActiveProfiles("test")
public class DataRepositoryTest {
    @Autowired
    DataRepository dataRepository;

    @Test
    @WithMockUser("admin")
    public void auditing() {
        Data data = new Data();

        data = dataRepository.save(data);

        assertThat(data.getId()).isNotNull();
        assertThat(data.getCreatedDate()).isNotNull();
        assertThat(data.getCreatedBy()).isEqualTo("admin");
        assertThat(data.getUpdatedDate()).isNotNull();
        assertThat(data.getUpdatedBy()).isEqualTo("admin");
    }
}