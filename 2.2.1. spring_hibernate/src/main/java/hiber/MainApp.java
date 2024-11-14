package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      Car car = new Car("bmw", 2011);
      Car car2 = new Car("mercedes", 2014);
      Car car3 = new Car("Vaz", 2003);
      Car car4 = new Car("Uaz", 2015);

      userService.add(new User("User6", "Lastname6", "user2@mail.ru", car2));
      userService.add(new User("User7", "Lastname7", "user3@mail.ru", car3));
      userService.add(new User("User8", "Lastname8", "user4@mail.ru", car4));
      userService.add(new User("User5", "Lastname5", "user1@mail.ru", car));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();


      }
      String carModel = "bmw";
      int carSeries = 2011;

      try {
         User user = userService.getUserByCar(carModel, carSeries);
         if (user != null) {
            System.out.println("Найден пользователь:");
            System.out.println(user);
         } else {
            System.out.println("Пользователь не найден.");
         }
      } catch (Exception e) {
         System.out.println("Ошибка при поиске пользователя по машине: " + e.getMessage());
      }

      context.close();
   }
}
