As of Spring Boot 1.5, if you have @ConfigurationProperties classes that use JSR-303 constraint annotations, you should now additionally annotate them with @Validated.
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Configuration-Binding
Update 20/05/2016: The code was updated to reflect the newest Spring Boot.

So we want to use the mail configuration as an example. The configuration file is placed in a separate file, called mail.properties. The properties must be named using a proper convention, so they can be bind properly. Let’s see some examples:

protocol and PROTOCOL will be bind to protocol field of a bean
smtp-auth, smtp_auth, smtpAuth will be bind to smtpAuth field of a bean
smtp.auth will be bind to … hmm to smtp.auth field of a bean!
Spring Boot uses some relaxed rules for binding properties to @ConfigurationProperties beans and supports hierarchical structure.

So let’s create a @ConfigurationProperties bean:

Note: As of Spring Boot 1.2 (December 2014) there is org.springframework.boot.autoconfigure.mail.MailProperties class similar to the below.

@ConfigurationProperties(locations = "classpath:mail.properties", ignoreUnknownFields = false, prefix = "mail")
public class MailProperties {

    public static class Smtp {

        private boolean auth;
        private boolean starttlsEnable;

        // ... getters and setters
    }

    @NotBlank
    private String host;
    private int port;  
    private String from;
    private String username;
    private String password;
    @NotNull
    private Smtp smtp;

    // ... getters and setters

}
… that should be created from the following properties (mail.properties):

mail.host=localhost
mail.port=25
mail.smtp.auth=false
mail.smtp.starttls-enable=false
mail.from=me@localhost
mail.username=
mail.password=
In the above example, we annotated a bean with @ConfigurationPropertiesso that Spring Boot can bind properties to it. ignoreUnknownFields = false tells Spring Boot to throw an exception when there are properties that do not match a declared field in the bean. This is pretty handy during the development! prefix let you select the name prefix of the properties to bind.

Please note that setters and getters should be created in @ConfigurationProperties bean! And opposite to @Value annotation it may bring some extra noise to the code (especially in simple cases, in my opinion).

Ok, but we want to use the properties to configure our application. There are at least two ways of creating @ConfigurationProperties. We can either use it together with @Configuration that provides @Beans or we can use it separately and inject into @Configuration bean.

The first scenario:

@Configuration
@ConfigurationProperties(locations = "classpath:mail.properties", prefix = "mail")
public class MailConfiguration {

    public static class Smtp {

        private boolean auth;
        private boolean starttlsEnable;

        // ... getters and setters
    }

    @NotBlank
    private String host;
    private int port;  
    private String from;
    private String username;
    private String password;
    @NotNull
    private Smtp smtp;

    // ... getters and setters   

    @Bean
    public JavaMailSender javaMailSender() {
        // omitted for readability
    }
}
In the second scenario, we simply annotate the properties bean (as above) and use Spring’s@Autowireto inject it into mail configuration bean:

@Configuration
public class MailConfiguration {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        // omitted for readability
    }
}
Note: The below is no more valid in Spring Boot. @ConfigurationProperties are enabled by default.

Please note @EnableConfigurationPropertiesannotation. This annotation tells Spring Boot to enable support for @ConfigurationProperties of a specified type. If not specified, you may otherwise see the org.springframework.beans.factory.NoSuchBeanDefinitionException exception.

To sum up, @ConfigurationProperties beans are pretty handy. Is it better than using @Value annotation? In certain scenarios probably yes, but this is just the choice you need to make.

Have a look at Spring Boot’s documentation to read more about typesafe configuration properties: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-typesafe-configuration-properties

See also: ${… } placeholders support in @Value annotations in Spring

Spring Boot Configuration Binding
The purpose of this page is to describe in detail how configuration keys are bound to actual objects. It is primarily targeted to IDE developers though anyone interested in understanding how binding works will find valuable resources in this document.

Note
Expected IDE features are described in a separate document.
Configuration structure
Regardless of the external format, the whole Environment boils down to a set of keys that is conceptually hierarchically structured. Let’s take an example:

server:
  port: 7070
  tomcat:
    max-threads: 20
This file contributes two keys: server.port and server.tomcat.port.max-threads. The latter belongs to the server.tomcat "group". Configuration items sharing a same concept are regrouped within the same prefix. Larger concepts have sub-concepts (for instance, the Apache Tomcat specific configuration is defined within the server.tomcat prefix).

Binding use cases
The most basic way to bind to a portion of the Environment is to define a POJO and inject a set of keys to it. As we will see later, Spring Boot offers several useful features in that area. But let’s first focus on the various binding use cases that are supported.

In the remainder of this section we will cover the following:

Simple property binding

Collection-based binding

Array-based binding

Map-based binding

Nested property

Note
while field-based binding is theoretically possible, Spring Boot only uses regular getter/setter access.
Simple property binding
Let’s take an example:

public class Foo {

    private String id = "";
    private int port;

    public String getId() { ... }
    void setId(String id) { ... }

    public int getPort() { ... }
    public void setPort(int port) { ... }
}
This object exposes one singular value property (port). id is not exposed as the setter for it is not public. A singular value is defined as the last portion of the key.

Note
the rule of thumb is that a valid public getter/setter pair should exist for the property to be exposed. In theory, the binder has enough information with only the setter but a singular property is exposed in the meta-data if both the getter and the setter are present.
Collection-based binding
When a property exposes a collection type, it is possible to contribute many values to it either by specifying the values in a comma-separated fashion or by using the bracket notation to indicate the index

public class Foo {

  private final List<String> items = new ArrayList<>();
  private Set<Integer> counters;

  public List<String> getItems() { .... }

  public Set<Integer> getCounters() { ... }
  public void setCounters(Set<Integer> counters) { ... }
}
The following keys will do what is expected (assuming foo is the context to which this object is bound to)

foo.items[1]=twoBis
foo.items[2]=four

foo.counters=1,2,3
Note
The rule of thumb is that a getter should be present to access the collection. If the collection needs to be initialized, a setter has to be provided.

Note
If Collection properties are specified in multiple property sources, only the elements from the property source with the highest precedence will be used. Properties from different property sources will NOT be combined when binding to a Collection. For example, if a .properties file has
foo[0] = 1
foo[1] = 2
and the Environment has

foo=3,4
the bound collection will only contain the elements 3 and 4.

Array-based binding
Binding of arrays requires a setter.

Map-based binding
As for collections, it is possible to contribute arbitrary values to a map by specifying the key using the bracket notation or using a navigation dot. A setter is only necessary if the map needs to be initialized

public class Foo {

  private final Map<String,Integer> items = new HashMap<>();

  public Map<String,Integer> getItems() { .... }

  private final Map<String,Map<String, Integer>> nested = new HashMap<>();

  public Map<String,Map<String, Integer>> getNested() { .... }
}
Here are some valid examples

foo.items.one=1
foo.items[two]=2
Note
If the key contains a dot, you must use the bracket notation if it is a nested Map. For example, for the above scenario, a valid example that will use the key bar.baz is
foo.items.bar.baz=1
foo.nested[bar.baz].bling=2
Nested property
There are several levels of nesting. So far we’ve used simple values but more complex objects can be processed. Let’s imagine the following object

public class Bar {
    private String id;
    private Integer counter;
    private boolean active;

    // getter and setter
}
A simple nesting would work as follows:

public class Foo {

  private final Bar bar = new Bar();

  public Bar getBar() { ... }

}
which would allow you to write the following

foo.bar.id=myId
foo.bar.counter=0
foo.bar.active=true
Tip
If you want Bar to be created on demand you can leave it null and add a setter instead.
Nesting works also for Collections and Arrays

public class Foo {

  private List<Bar> bars = new ArrayList<>();

  public List<Bar> getBars() { .... }
  public void setBars(List<Bar> bars) { ... }
}
which permits the nesting on an index element

foo.bars[0].id=one
foo.bars[2].counter=3
Tip
it is not possible to give a comma separated-view of such object unless a Converter<String,Bar> is registered in the ConversionService of the binder.
As you may imagine, this works for maps as well:

public class Foo {

  private Map<String, Bar> bars = new HashMap<>();

  public Map<String, Bar> getBars() { .... }
}
foo.bars.one.counter=1
foo.bars.one.active=false
foo.bars[two].id=IdOfBarWithKeyTwo
Finally, nesting can use as many level as you want. The . is used as the character to navigate between those relationships. Let’s consider this rather complex key:

foo.items.myKey.customer.address.street=Acme street
This sets the street of the customer that is referenced with the key myKey in the items map, something like (assuming foo is the reference of our root object):

foo.get("myKey").getCustomer().getAddress().setStreet("Acme street")
Tip
If an intermediate relationship is null, a new instance will be created using the default constructor and the related setter will be called with it.
Wrapping Up
The table below describes the rules regarding getter/setter presence

Table 1. Getter and setter
Binding type

Getter

Setter

Example

Simple

Not technically necessary

Required

foo.name=myName

Collection

Required

Not required if collection is initialized

foo.items=1,2,3

List

Required

Not required if the collection is initialized

foo.items[1]=2

Array

Required

Required

foo.items[1]=2

Map

Required

Not required if the map is initialized

foo.items.one=1

Navigation

Required

Not required if the instance is initialized

foo.bar.name=myName

Declarative binding
Spring Boot provides a @ConfigurationProperties annotation that can be placed on any object to declare its root prefix. It then uses that with an augmented binder to automatically bind properties from the Environment for matching prefixes. It also exposes the object as a Spring Bean automatically.

@ConfigurationProperties("foo")
public class FooProperties {

    private String id = "";
    private final Bar bar = new Bar;

    public String getId() { ... }
    pulic void setId(String id) { ... }

    public Bar getBar() { ... }

    static class Bar {
        private String name;
        private boolean active;

        public String getName() { ... }
        public void setName(String name) { ... }

        public boolean isActive() { ... }
        public void setActive(boolean active) { ... }
    }
}
If the processing of that object is registered via, for instance, @EnableConfigurationProperties(FooProperties.class), a bean of type FooProperties will be automatically registered in the context and the following keys will be bound as you expect:

foo.id=myId
foo.bar.name=barName
foo.bar.active=true
Tip
All the other binding types that we have seen above would obviously work here as well.
You could also apply the same mechanism on a @Bean declaration. In that case, binding will be applied once the object has been built. This is typically useful to provide a friendly configuration for a third party object.

Let’s consider that FooProperties is some 3rd party Foo class that we use within our Spring Boot application:

@Configuration
public class MyConfig {

  @Bean
  @ConfigurationProperties("foo")
  public Foo foo() {
      Foo foo = new Foo();
      foo.setId("some id");
      return foo;
  }
}
This will expose the exact same set of properties (since we used the same prefix as the example above). Note also that if the configuration define a foo.id=anotherId entry, the id of that bean will be ultimately anotherId as property binding is applied once the object has been built.

Relaxed binding
Because keys can be defined in various formats and certain sources have some limitations, Spring Boot uses a relaxed binder. Consider the following

@ConfigurationProperties("foo")
public class FooProperties {

    private String id;
    private String firstName;
    private String lastName;

    // getters and setters

}
Spring Boot uses a canonical format that is lower case and use hyphen to separate words. But other formats are supported as defined in the table below:

Table 2. Key formats
Name

Example

uniform

foo.id - foo.first-name - foo.last-name

camel case

foo.id - foo.firstName - foo.lastName

underscore

foo.id - foo.first_name - foo.last_name

upper case

FOO_ID - FOO_FIRST-NAME - FOO_LAST-NAME

Note
There are actually many more variants that are supported
The main reason behind relaxed binding is to offer flexibility, particularly when the property source does not support certain characters: OS environment variables must be upper case or could not contain a dot on certain OSes. Details about relaxed binding as per property source can be found here.

Configuration meta-data
This section describes how meta-data is concretely discovered and some of the limitations around it.

Note
If you haven’t done so, you should first read the configuration meta-data section of the developer guide as it defines the base concepts used in this section.
The purpose of the meta-data is to provide a static model of the configuration keys so that tools can benefit from it and offer content assistance to the users. Meta-data is generated automatically during the compilation when the spring-boot-configuration-processor is available.

The properties are a finite set of what is exposed by the current module. While for instance foo.items[myKey].address.street is a valid configuration key, the meta-data will only expose the foo.items key with a type that offers the necessary to discover the rest, that is:

The type is a Map

The key is a java.lang.String

The value is com.acme.Person (referenced in the rest of this document as a Simple POJO) that could be further investigated for additional accessors (a Person has an Address and an Address has a street)

Singular value vs. nested property
Cases such as Collection or Map-based bindings are easy to discover because the type of the property says so. It is harder to figure out whether any other type of property is a single value or represent a concept we should navigate to.

To take back the previous example, we could have wrote foo.bar=??? which would have failed obviously since there is not setter and the intention of that object is to offer an object exposing additional properties.

Spring Boot uses the following rule:

If the type of the property is an inner class of the current object, it is considered to be a nested property (we use this pattern a lot and found it nice that it could be auto-discovered. Check ServerProperties for instance)

If the property is flagged with @NestedConfigurationProperty, Spring Boot consider it to be a nested property (See Ssl for an example)

In all other cases, the property is supposed to be a singular value.

Let’s consider that Bar is now defined in a different area (in the same package or in a different package but not as an inner class), we could rewrite our class to ensure that meta-data are discovered in the same way:

import com.acme.Bar;

@ConfigurationProperties("foo")
public class FooProperties {

    private String id = "";

    @NestedConfigurationProperty
    private final Bar bar = new Bar;

    public String getId() { ... }
    public void setId(String id) { ... }

    public Bar getBar() { ... }

}
Default value
Default values can only be discovered by using low-level utility of the compiler API. Spring Boot has support for the Oracle JDK but does not support yet the Eclipse compiler (APT).

The default value should be set preferably in the field declaration itself. If a public static constant is set in the class itself, the processor will discover it as well.

For instance

@ConfigurationProperties("my")
public class MyProperties {

    public static final int DEFAULT_INDEX = 0;

    private String name = "myName";
    private int index = DEFAULT_INDEX;

    // getter and setter
}
Documentation
Documentation is only extracted from field Javadoc. The main reason behind this decision is that the description of a configuration key may greatly differ from the one you usually write for a setter. Besides, the processor does not clean any of the Javadoc tag that is present so having a separate location for the documentation is necessary.

Note
If the property does not have a related field or if the field does not match the convention, the documentation is not available.
The Javadoc is only obviously accessible if the source code is available. No description is therefore available for:

Keys defined in the parent of a @ConfigurationProperties class if said base class is not in the current compilation unit (i.e. module).

@Bean exposing a third party class

One way to mitigate that problem is to upgrade the IDE support so that it can fetch that documentation live if it exists rather than only relying on the meta-data.
