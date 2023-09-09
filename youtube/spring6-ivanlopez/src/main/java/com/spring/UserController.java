package com.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.observation.annotation.Observed;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @Observed(name ="users", contextualName = "all")
    List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    @Observed(name="users" , contextualName = "byId")
    ResponseEntity<?> byId(@PathVariable Long id) throws Exception {
        // return this.userRepository.findById(id)
        // .map(ResponseEntity::ok)
        // .orElseThrow(UserNotFoundException::ex);
       try {
            Optional<User> usuario = this.userRepository.findById(id);
            if (usuario == null || !usuario.isPresent()) {
                throw new UserNotFoundException("User Not Found Onda : " + id);
            }
            return ResponseEntity.ok(usuario.get());
        } catch (UserNotFoundException ex) {
            System.out.println("ingreso por aqui que onda");
             throw new UserNotFoundException("User Not Found Onda : " + id);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
      
    }



  // Problem Detail ingresa en spring 6
    @ControllerAdvice 
    class ErrorHandler  {


        /**
         * Formta de Error
         * @param e
         * @return
         */
        @ExceptionHandler(UserNotFoundException.class)
        public ProblemDetail handleNotFound(UserNotFoundException e) {
            ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "The user does not exist");

            pd.setTitle("User not found");
            pd.setProperty("some-property", "manuonda qu eonda");
            return pd;
        }
    }
  

}

