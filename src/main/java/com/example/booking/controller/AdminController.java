package com.example.booking.controller;

import com.example.booking.model.*;
import com.example.booking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ConcertService concertService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PerformerService performerService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private VenueService venueService;

    @GetMapping
    public String showAdminDashboard(Model model, Principal pr) {
        model.addAttribute("concerts", concertService.findAll());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("performers", performerService.findAll());
        model.addAttribute("reviews", reviewService.findAll());
        model.addAttribute("tickets", ticketService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("venues", venueService.findAll());

        model.addAttribute("username", pr.getName());
        return "admin_dashboard";
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.update(id, user);
        return "redirect:/admin"; // Перенаправление на страницу администратора
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_edit"; // Возвращает страницу редактирования пользователя
    }

    @PostMapping("/users/update")
    public String updateUser(User user, Model model) {
        userService.update(user.getId(), user); // Обновите пользователя
        return "redirect:/admin"; // Перенаправление обратно на панель администратора
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin"; // Перенаправление обратно на панель администратора
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add_user"; // Возвращает страницу добавления пользователя
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.registerUser(user); // Регистрация пользователя
        return "redirect:/admin"; // Перенаправление обратно на панель администратора
    }

    @GetMapping("/concerts/add")
    public String showAddConcertForm(Model model) {
        model.addAttribute("concert", new Concert()); // This should correctly add a new Concert object to the model
        return "add_concert"; // Make sure this matches the name of your Thymeleaf template
    }

    @PostMapping("/concerts/add")
    public String addConcert(@ModelAttribute Concert concert) {
        concertService.save(concert); // Save the new concert using your service layer
        return "redirect:/admin/concerts"; // Redirect to the concert list after adding
    }


    @GetMapping("/concerts/edit/{id}")
    public String editConcert(@PathVariable Long id, Model model) {
        Concert concert = concertService.findById(id);
        model.addAttribute("concert", concert);
        return "concert_edit"; // Ваш шаблон для редактирования
    }

    @PostMapping("/concerts/update/{id}")
    public String updateConcert(@PathVariable Long id, @ModelAttribute Concert concert) {
        concertService.update(id, concert); // Обновите концерт
        return "redirect:/admin/concerts"; // Перенаправление на страницу списка концертов
    }

    @PostMapping("/concerts/delete/{id}")
    public String deleteConcert(@PathVariable Long id) {
        concertService.delete(id);
        return "redirect:/admin/concerts"; // Перенаправление на страницу списка концертов
    }



    @GetMapping("/concerts")
    public String showConcerts(Model model) {
        List<Concert> concerts = concertService.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        // Форматируем дату для каждого концерта
        concerts.forEach(concert -> {
            String formattedDate = concert.getDateTime().format(formatter);
        });

        model.addAttribute("concerts", concerts);
        return "admin_dashboard"; // Ваш шаблон
    }

    @GetMapping("/performers")
    public String showPerformers(Model model) {
        model.addAttribute("performers", performerService.findAll());
        return "admin_dashboard"; // Ensure this template exists and matches your needs
    }

    @GetMapping("/performers/add")
    public String showAddPerformerForm(Model model) {
        model.addAttribute("performer", new Performer());
        return "add_performer"; // Create this template
    }

    @PostMapping("/performers/add")
    public String addPerformer(@ModelAttribute Performer performer) {
        performerService.save(performer);
        return "redirect:/admin/performers"; // Redirect to performers list after adding
    }


    @GetMapping("/performers/edit/{id}")
    public String editPerformer(@PathVariable Long id, Model model) {
        model.addAttribute("performer", performerService.findById(id));
        return "edit_performer"; // Create this template
    }

    @PostMapping("/performers/update/{id}")
    public String updatePerformer(@PathVariable Long id, @ModelAttribute Performer performer) {
        performerService.update(id, performer);
        return "redirect:/admin";
    }

    @PostMapping("/performers/delete/{id}")
    public String deletePerformer(@PathVariable Long id) {
        performerService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/venues")
    public String showVenues(Model model) {
        model.addAttribute("venues", venueService.findAll());
        return "admin_dashboard"; // Ensure this template exists and matches your needs
    }

    @GetMapping("/venues/add")
    public String showAddVenueForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "add_venue"; // Create this template
    }

    @PostMapping("/venues/add")
    public String addVenue(@ModelAttribute Venue venue) {
        venueService.save(venue);
        return "redirect:/admin/venues"; // Redirect to venues list after adding
    }

    @GetMapping("/venues/edit/{id}")
    public String editVenue(@PathVariable Long id, Model model) {
        model.addAttribute("venue", venueService.findById(id));
        return "edit_venue"; // Create this template
    }

    @PostMapping("/venues/update/{id}")
    public String updateVenue(@PathVariable Long id, @ModelAttribute Venue venue) {
        venueService.update(id, venue);
        return "redirect:/admin/venues"; // Redirect to venues list after updating
    }

    @PostMapping("/venues/delete/{id}")
    public String deleteVenue(@PathVariable Long id) {
        venueService.delete(id);
        return "redirect:/admin/venues"; // Redirect to venues list after deleting
    }

    @GetMapping("/reviews")
    public String showReviews(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "admin_dashboard"; // Ensure the template supports this view or change the template if needed
    }

    @GetMapping("/reviews/add")
    public String showAddReviewForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("concerts", concertService.findAll());
        return "add_review"; // Create this template
    }

    @PostMapping("/reviews/add")
    public String addReview(@ModelAttribute Review review) {
        reviewService.save(review);
        return "redirect:/admin/reviews"; // Redirect to the reviews list after adding
    }

    @GetMapping("/reviews/edit/{id}")
    public String editReview(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("concerts", concertService.findAll());
        return "edit_review"; // Create this template
    }

    @PostMapping("/reviews/update/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute Review review) {
        reviewService.update(id, review);
        return "redirect:/admin/reviews"; // Redirect to the reviews list after updating
    }

    @PostMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return "redirect:/admin/reviews"; // Redirect to the reviews list after deleting
    }

    @GetMapping("/orders")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "admin_dashboard"; // Убедитесь, что шаблон поддерживает отображение заказов или измените его на нужный
    }

    @GetMapping("/orders/add")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("users", userService.findAll()); // Подгружаем пользователей для выбора
        return "add_order"; // Создайте шаблон add_order.html
    }

    @PostMapping("/orders/add")
    public String addOrder(@ModelAttribute Order order) {
        // Найдите пользователя и прикрепите его к контексту
        User managedUser = userService.findById(order.getUser().getId());
        order.setUser(managedUser);

        orderService.save(order);
        return "redirect:/admin/orders"; // Перенаправление после добавления заказа
    }


    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("users", userService.findAll()); // Подгружаем пользователей для выбора
        return "edit_order"; // Создайте шаблон edit_order.html
    }

    @PostMapping("/orders/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        orderService.update(id, order);
        return "redirect:/admin/orders"; // Перенаправление после обновления
    }

    @PostMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return "redirect:/admin/orders"; // Перенаправление после удаления
    }

    @GetMapping("/payments")
    public String showPayments(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        return "admin_dashboard"; // Убедитесь, что ваш шаблон поддерживает отображение списка оплат
    }

    @GetMapping("/payments/add")
    public String showAddPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("users", userService.findAll()); // Подгружаем пользователей для выбора
        return "add_payment"; // Создайте шаблон add_payment.html
    }

    @PostMapping("/payments/add")
    public String addPayment(@ModelAttribute Payment payment) {
        User managedUser = userService.findById(payment.getUser().getId());
        payment.setUser(managedUser);

        paymentService.save(payment);
        return "redirect:/admin/payments"; // Перенаправление после добавления платежа
    }

    @GetMapping("/payments/edit/{id}")
    public String editPayment(@PathVariable Long id, Model model) {
        model.addAttribute("payment", paymentService.findById(id));
        model.addAttribute("users", userService.findAll()); // Подгружаем пользователей для выбора
        return "edit_payment"; // Создайте шаблон edit_payment.html
    }

    @PostMapping("/payments/update/{id}")
    public String updatePayment(@PathVariable Long id, @ModelAttribute Payment payment) {
        paymentService.update(id, payment);
        return "redirect:/admin/payments"; // Перенаправление после обновления
    }

    @PostMapping("/payments/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
        return "redirect:/admin/payments"; // Перенаправление после удаления
    }








}
