package ru.baydak.pizza.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.baydak.pizza.domain.dto.UserRegisterDTO;
import ru.baydak.pizza.domain.model.Product;
import ru.baydak.pizza.domain.model.User;
import ru.baydak.pizza.mapper.UserMapper;
import ru.baydak.pizza.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthService authService;

    @Mock
    private PizzaService pizzaService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSave() {
        User user = new User();
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByUsername("testUser");
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testFindById() {
        User user = new User();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Optional<User> result = userService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testRegister() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        User user = new User();
        when(userMapper.registerDTOToUser(userRegisterDTO)).thenReturn(user);
        userService.register(userRegisterDTO);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAddToCartByProductId() {
        User user = new User();
        user.setId(1);
        Product product = new Product();
        product.setId(1);
        product.setPrice(100.0);
        when(authService.getAuthUser()).thenReturn(Optional.of(user));
        when(pizzaService.getById(anyInt())).thenReturn(product);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        userService.addToCartByProductId(1);

        verify(userRepository, times(1)).save(user);
        assertEquals(1, user.getProducts().size());
        assertEquals(product, user.getProducts().get(0));
    }

    @Test
    public void testGetCart() {
        User user = new User();
        user.setId(1);
        Product product = new Product();
        product.setId(1);
        product.setPrice(100.0);
        user.getProducts().add(product);
        when(authService.getAuthUser()).thenReturn(Optional.of(user));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        List<Product> cart = userService.getCart();

        verify(userRepository, times(1)).findById(user.getId());
        assertEquals(1, cart.size());
        assertEquals(product, cart.get(0));
    }

    @Test
    public void testRemoveFromCartByProductId() {
        User user = new User();
        user.setId(1);
        Product product = new Product();
        product.setId(1);
        product.setPrice(100.0);
        user.getProducts().add(product);
        when(authService.getAuthUser()).thenReturn(Optional.of(user));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        userService.removeFromCartByProductId(1);

        verify(userRepository, times(1)).save(user);
        assertEquals(0, user.getProducts().size());
    }
}
