package edu.kpi.jee.cityguide.repositories;

import edu.kpi.jee.cityguide.entities.Place;
import edu.kpi.jee.cityguide.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlaceRepository placeRepository;

    @MockBean
    User user;

    @Test
    public void whenFindByAuthor_thenReturnAircraft() {
        // given
        Mockito.when(user.getId()).thenReturn(10);
        Place place = new Place();
        place.setAuthor(user);
        entityManager.persist(place);
        entityManager.flush();

        // when
        List<Place> found = placeRepository.findByAuthor(place.getAuthor());

        // then
        assertEquals(found.get(0).getAuthor().getId(), place.getAuthor().getId());
    }
}