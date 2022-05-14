package dev.saadat.datenbanken;

import java.util.List;
import java.util.Optional;

import dev.saadat.datenbanken.models.User;
import dev.saadat.datenbanken.services.LiquibaseService;
import dev.saadat.datenbanken.services.UserService;
import dev.saadat.datenbanken.utilities.HibernateSessionFactory;


public class Datenbanken {


    public Datenbanken() { }

    public void create() {
        System.out.println("[CREATE] Creating 3 entities...");
        try (UserService userService = new UserService()) {
            User u1 = new User.Builder()
                    .name("UserA")
                    .address(456L)
                    .build();

            User u2 = new User.Builder()
                    .name("UserB")
                    .address(123L)
                    .build();

            User u3 = new User.Builder()
                    .name("UserC")
                    .address(789L)
                    .build();

            userService.addUsers(u1, u2, u3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void read() {
        try (UserService userService = new UserService()) {
            System.out.println("[READ] Reading all entries...");
            List<User> userList = userService.getAllUsers();
            System.out.printf("=> Available Users: %d\n", userList.size());
            for (User u: userList) {
                System.out.printf("+--- User #%d\n--> name: %s\n--> addr: %d\n\n", u.id, u.name, u.address);
            }

            System.out.println("[READ] Reading entity by id: 2");
            Optional<User> optionalUser = userService.getUserById(2L);
            if (optionalUser.isPresent()) {
                User u = optionalUser.get();
                System.out.printf("+--- User #%d\n--> name: %s\n--> addr: %d\n\n", u.id, u.name, u.address);
            } else {
                System.out.println("User not found!");
            }

            System.out.println("[READ] Attempting invalid read with id: 9");
            Optional<User> optionalUser_2 = userService.getUserById(9L);
            if (optionalUser_2.isPresent()) {
                User u = optionalUser_2.get();
                System.out.printf("+--- User #%d\n--> name: %s\n--> addr: %d\n\n", u.id, u.name, u.address);
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try (UserService userService = new UserService()) {
            System.out.println("[UPDATE] updating entity with id: 2");
            userService.updateUserAddressById(2L, 678L);
            List<User> userList = userService.getAllUsers();
            System.out.printf("=> Available Users: %d\n", userList.size());
            for (User u: userList) {
                System.out.printf("+--- User #%d\n--> name: %s\n--> addr: %d\n\n", u.id, u.name, u.address);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try (UserService userService = new UserService()) {
            System.out.println("[DELETE] deleting entities with id 2 and address greater than 100...");
            userService.removeUserById(2L);
            userService.removeUserIfAddressGreaterThan(100L);
            List<User> userList = userService.getAllUsers();
            System.out.printf("=> Available Users: %d\n", userList.size());
            for (User u: userList) {
                System.out.printf("+--- User #%d\n--> name: %s\n--> addr: %d\n\n", u.id, u.name, u.address);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        try (LiquibaseService liquibase = new LiquibaseService()) {
            liquibase.runChangelogs();

            Datenbanken db = new Datenbanken();
            db.create();
            db.read();
            db.update();
            db.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateSessionFactory.close();
    }


    /* End */
}