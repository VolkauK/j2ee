# Copilot Instructions

## Build & Test

```bash
# Build (produces target/demo-1.0-SNAPSHOT.war)
./mvnw package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=MyTestClass

# Run a single test method
./mvnw test -Dtest=MyTestClass#myMethod
```

The app does **not** run standalone — it must be deployed to a Jakarta EE 11 compatible server (GlassFish 8, WildFly 35+, Payara 6+, Open Liberty 23+).

## Architecture

This is a **Jakarta EE 11** web application (WAR) structured around three Jakarta APIs:

- **JAX-RS** (`HelloApplication`, `@ApplicationPath("/api")`) — all REST endpoints live under `/api`. Resources are plain classes annotated with `@Path`.
- **Jakarta MVC 3.0 / Eclipse Krazo** — included as a dependency alongside JAX-RS for server-side MVC (controllers + views). MVC controllers use `@Controller` together with JAX-RS annotations.
- **JPA / Hibernate 7** — persistence unit named `"default"` in `META-INF/persistence.xml`. No datasource is configured yet; add a `<jta-data-source>` (provided by the app server) when adding entities.
- **CDI 4.1** — enabled by `META-INF/beans.xml`; use `@Inject` freely.

Base package: `example.demo`

## Key Conventions

- **Packaging is WAR** — there is no embedded server. Build with `./mvnw package`, then deploy the WAR.
- **Jakarta EE, not Spring** — use `jakarta.*` imports throughout, never `javax.*`. The full Jakarta EE Web Profile is on the provided classpath.
- **Hibernate is the JPA provider** — annotate entities with `@Entity` (from `jakarta.persistence`). JAXB runtime is included as a required Hibernate dependency; don't remove it.
- **MVC vs JAX-RS** — use plain JAX-RS (`@GET`, `@Produces`, etc.) for REST/JSON endpoints; use `@Controller` (Jakarta MVC) for HTML responses with view templates.
- **CDI discovery** — `beans.xml` is present but empty, so discovery mode defaults to `annotated`. Beans must be explicitly annotated (e.g., `@RequestScoped`, `@ApplicationScoped`).
- **Java 21** — records, sealed classes, pattern matching, and text blocks are all available.
