package com.kjh.clean_stock.web;

import com.kjh.clean_stock.web.controller.ProfileController;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다(){
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없음_첫번째_조회(){
        String expectedProfile = "oauth";
        MockEnvironment env= new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");
        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }
    @Test
    public void active_profile이_없음_default_조회(){
        String expectedProfile = "default";
        MockEnvironment env= new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }
}
