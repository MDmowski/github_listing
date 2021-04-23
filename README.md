# Allegro Summer e-Xperience 2021 - Zadanie 3

## Spis treści

* [Cel zadania](#cel-zadania)
* [Technologie](#technologie)
* [Intrukcja uruchomienia](#instrukcja-uruchomienia)
* [Intrukcja użytkowania](#instrukcja-użytkowania)

## Cel zadania

- listowanie repozytoriów dowolnego użytkownika (nazwa i liczba gwiazdek)
- zwracanie sumy gwiazdek we wszystkich repozytoriach dowolnego użytkownika

Dane powinny być zwracane za pomocą protokołu HTTP.

## Technologie

Aplikacja stworzona w Springu. Testowana za pomocą JUnit, AssertJ i Mockito.

## Instrukcja uruchomienia

`./mvnw spring-boot:run`

## Instrukcja użytkowania

### Listowanie repozytoriów użytkownika

Zwraca listę wszystkich repozytoriów użytkownika - nazwa repozytorium i liczba gwiazdek.

#### Struktura URL

```http request
http://localhost:8080/list?username={user}
```

#### Przykład

```http request
http://localhost:8080/list?username=allegro
```

#### Wyjście

```json
[
  {
    "name": "akubra",
    "stargazers_count": 79
  },
  {
    "name": "allegro-api",
    "stargazers_count": 132
  },
  {
    "name": "allegro-tech-labs-iot",
    "stargazers_count": 1
  },
  {
    "name": "allegro-tech-labs-microservices",
    "stargazers_count": 6
  },
  {
    "name": "allegro.tech",
    "stargazers_count": 20
  },
  {
    "name": "allRank",
    "stargazers_count": 289
  },
  {
    "name": "apt-repo",
    "stargazers_count": 0
  },
  {
    "name": "atm-event-admin",
    "stargazers_count": 0
  },
  {
    "name": "atm-event-app",
    "stargazers_count": 5
  },
  {
    "name": "atm-event-scanner",
    "stargazers_count": 0
  },
  {
    "name": "axion-release-plugin",
    "stargazers_count": 380
  },
  {
    "name": "bigcache",
    "stargazers_count": 4792
  },
  {
    "name": "bigcache-bench",
    "stargazers_count": 3
  },
  {
    "name": "bigflow",
    "stargazers_count": 60
  },
  {
    "name": "braincode",
    "stargazers_count": 3
  },
  {
    "name": "braincode.mobi",
    "stargazers_count": 2
  },
  {
    "name": "camus-compressor",
    "stargazers_count": 12
  },
  {
    "name": "cassandra-modeling-kata",
    "stargazers_count": 35
  },
  {
    "name": "consul",
    "stargazers_count": 0
  },
  {
    "name": "consul-recipes",
    "stargazers_count": 10
  },
  {
    "name": "consul-registration-hook",
    "stargazers_count": 15
  },
  {
    "name": "django-bob",
    "stargazers_count": 6
  },
  {
    "name": "django-powerdns-dnssec",
    "stargazers_count": 27
  },
  {
    "name": "elasticsearch-analysis-morfologik",
    "stargazers_count": 60
  },
  {
    "name": "elasticsearch-reindex-tool",
    "stargazers_count": 42
  },
  {
    "name": "embedded-elasticsearch",
    "stargazers_count": 258
  },
  {
    "name": "envoy",
    "stargazers_count": 0
  },
  {
    "name": "envoy-control",
    "stargazers_count": 67
  },
  {
    "name": "envoy-perf-pprof",
    "stargazers_count": 4
  },
  {
    "name": "fogger",
    "stargazers_count": 59
  }
]
```

### Zwracanie sumy gwiazdek we wszystkich repozytoriach użytkownika

Zwraca sumę gwiazdek wszystkich repozytoriów użytkownika.

#### Struktura URL

```http request
http://localhost:8080/stars?username={user}
```

#### Przykład

```http request
http://localhost:8080/stars?username=allegro
```

#### Wyjście

```json
6367
```
