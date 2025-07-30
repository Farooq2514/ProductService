package dev.umar.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    RestTemplate restTemplate;
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
/*why rest template
* The reason why we need to create Rest Template class ?
the reason of creating a rest template of a class because if there are 5 different classes which need to to work with external api at that time each classs or service is going to create a object of the rest template  ,
so we create a class and inject in the class where ever we needed to inject it
where we dont want to create a new object in all the 5 classes instead just create rest template class and inject it .
for this for the spring recognize that this is rest template class (rememeber that rest template class is already create internal )
we are just importing .
*
* spring beans
is nothing but an object spring has created and keep in the application context any where else any other class if need of that object in that class spring will keep that object in that class
way of creating a bean is just annotate the method that returns the object as "@Bean"
now it automatically inject wherever we needed
* */