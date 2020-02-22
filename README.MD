# Split test type using profile

Main purpose is for what developers can write pure unit test without environment configuration and also can run integration/auto test for advanced test.

## Run test

There are profile `unit` and `integration` in this project. `integration` required a database connection for test, this could use `docker-compose.yml` file for configuration, and `unit` doesn't need anything, it just runs.

```shell script
# run unit test (deafult profile)
mvn clean test
# run unit test
mvn clean test -Punit

# run integration test
mvn clean test -Pintegration
```

## Profiles

There are two kind of profiles. One is `maven profile` and another is `spring boot profile`. Need to understand the difference of these two profile then get the correct way to run test.

### Maven profile

Maven profile is configured in `pom.xml` and only works for maven command such as `mvn clean test -Punit`. Maven profile have two points need to specified: `spring boot profile` and `test class naming patter`. Previous one indicate to use the profile in spring boot, and then next one decide which tests should be executed.

```xml
<profiles>
    <profile>
        <id>unit</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <!-- spring boot profile, filled into application.properties -->
            <profile.active>unit</profile.active>
        </properties>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <configuration>
                        <!-- specify unit test class by path pattern -->
                        <includes>
                            <include>**/UniteBaseTest.java</include>
                            <include>*unit.*/*.java</include>
                        </includes>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
    <profile>
        <id>integration</id>
        <properties>
            <!-- spring boot profile, filled into application.properties -->
            <profile.active>integration</profile.active>
        </properties>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <configuration>
                        <!-- include all test -->
                        <includes>
                            <include>*/*.java</include>
                        </includes>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
```

### Spring boot profile

Spring boot profile indicate what kind of environment we will use in runtime. It may configured within a service or a configuration. As a example below, it defined two bean with same name, but split with different profile `unit` and `integration`. When using `unit` profile, there is no database connection available, so the implementation just return a mock of flyway object. In another way, implementation of `integration` profile initialized with existing connection and return a flayway instance. 

```java
@Configuration
public class FlywayConfig {

    @Profile({"integration"})
    @Bean("flyway")
    public Flyway flyway1(@Autowired DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        return flyway;
    }

    @Profile({"unit"})
    @Bean("flyway")
    public Flyway flyway2() {
        return Mockito.mock(Flyway.class);
    }

}
```

### Intellij IDEA support

Since IDEA run unit test with it's own way (not depend on maven), it's difficult to handle different kind of test concept. When running a single test, it could add `SPRING_PROFILES_ACTIVE` environment variable to select the profile we want, but if we attempt run all test for a project, this will mix different type of test and than may cause failed for some.
