org.springframework.cloud
spring-cloud-starter-netflix-hystrix


the fault tolerance library

: Hystrix is watching methods for failing calls to related services. If there is such a failure, it will open the circuit and forward the call to a fallback method.

The library will tolerate failures up to a threshold. Beyond that, it leaves the circuit open. Which means, it will forward all subsequent calls to the fallback method, to prevent future failures. This creates a time buffer for the related service to recover from its failing state.

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
    <version>1.1.5.RELEASE</version>
</dependency>
For the Circuit Breaker to work, Hystix will scan @Component or @Service annotated classes for @HystixCommand annotated methods, implement a proxy for it and monitor its calls.

This will be our injectable @Service implementing a @HystrixCommand with an associated fallback method. This fallback has to use the same signature as the ‘original’:

@Service
public class GreetingService {
    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {
        return new RestTemplate()
          .getForObject("http://localhost:9090/greeting/{username}", 
          String.class, username);
    }
  
    private String defaultGreeting(String username) {
        return "Hello User!";
    }
}


 The @EnableCircuitBreaker annotation will scan the classpath for any compatible Circuit Breaker implementation.

To use Hystrix explicitly, you have to annotate this class with @EnableHystrix:

@SpringBootApplication
@EnableCircuitBreaker
public class RestConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestConsumerApplication.class, args);
    }
}
