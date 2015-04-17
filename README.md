
# Filtering, sorting, mapping

### Backgrounds
[Guest.java](src/main/java/com/naver/helloworld/resort/domain/Guest.java)

```java
public class Guest {
	private final int grade;
	private final String name;
	private final String company;
...
}
```

[GuestRepository.java](src/main/java/com/naver/helloworld/resort/repository/GuestRepository.java)

```java
import java.util.List;

public interface GuestRepository {
	public List<Guest> findAllGuest ();
}
```

[ResortService.java](src/main/java/com/naver/helloworld/resort/service/ResortService.java)

```java
public interface ResortService {
	public List<String> findGuestNamesByCompany (String company);
}
```

## Implementations by classic Java
### JDK Collections framework
[ClassicJavaResort.java](src/main/java/com/naver/helloworld/resort/service/ClassicJavaResort.java)

```java
public List<String> findGuestNamesbyCompany(String company) {
	List<Guest> all = repository.findAllGuest();

	List<Guest> filtered = filter(guests, company);
	sort(filtered);
	return mapNames(filtered);
}

private List<Guest> filter(List<Guest> guests, String company) {
	List<Guest> filtered = new  ArrayList<>();
	for(Guest guest : guests ) {
		if (company.equals(guest.getCompany())) {
			filtered.add(guest);
		}
	}
	return filtered;
}

private void sort(List<Guest> guests) {
	Collections.sort(guests, new Comparator<Guest>() {
		public int compare(Guest o1, Guest o2) {
			return Integer.compare(o1.getGrade(), o2.getGrade());
		}
 	});
}

private List<String> mapNames(List<Guest> guests) {
	List<String> names = new ArrayList<>();
	for(Guest guest : guests ) {
		names.add(guest.getName());
	}
	return names;
}
```

### [Guava](https://github.com/google/guava)
[GuavaResort.java](src/main/java/com/naver/helloworld/resort/service/GuavaResort.java)

```java
public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAll();

	List<Guest> sorted = FluentIterable.from(all)
		.filter(new Predicate<Guest>() {
			public boolean apply(Guest g) {
				return company.equals(g.getCompany());
			}
	})
	.toSortedList(Ordering.natural().onResultOf(
		new Function<Guest, Integer>() {
			public Integer apply(Guest g) {
				return g.getGrade();
		}
	}));

	return FluentIterable.from(sorted)
			.transform(new Function<Guest, String>() {
				public String apply(Guest g) {
					return g.getName();
				}
			})
			.toList();
}
```

### [Totally Lazy](http://totallylazy.com/)
[TotallyLazyResort.java](src/main/java/com/naver/helloworld/resort/service/TotallyLazyResort.java)

```java
public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAll();
	return Sequences.sequence(all)
		.filter(new Predicate<Guest>() {
			public boolean matches(Guest g) {
				return company.equals(g.getCompany());
			}
		})
		.sortBy(new Callable1<Guest, Integer>(){
			public Integer call(Guest g) {
				return g.getGrade();
			}
		})
		.map(new Callable1<Guest, String>(){
			public String call(Guest g) {
				return g.getName();
			}
		})
		.toList();
}
```

### [GS Collections](https://github.com/goldmansachs/gs-collections)
[GsCollectoinsResort.java](src/main/java/com/naver/helloworld/resort/service/GsCollectionsResort.java)

```java
public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAll();
	return FastList.newList(all)
		.select(new Predicate<Guest>() {
			public boolean accept(Guest g) {
				return company.equals(g.getCompany());
			}
		})
		.sortThisBy(new Function<Guest, Integer>() {
			public Integer valueOf(Guest g) {
				return g.getGrade();
			}
		})
		.collect(new Function<Guest, String> () {
			public String valueOf(Guest g) {
				return g.getName();
			}
		});
}
```

### [Bolts](https://bitbucket.org/stepancheg/bolts/wiki/Home)
[BoltsResort.java](src/main/java/com/naver/helloworld/resort/service/BoltsResort.java)

```java
	public List<String> findGuestNamesByCompany(final String company) {
		List<Guest> all = repository.findAllGuest();
		return Cf.list(all)
			.filter(new Function1B<Guest>() {
				public boolean apply(Guest g) {
					return company.equals(g.getCompany());
				}
			})
			.sortBy(new Function<Guest, Integer>() {
				public Integer apply(Guest g) {
					return g.getGrade();
				}
			})
			.map(new Function<Guest, String>() {
				public String apply(Guest g) {
					return g.getName();
				}
			});
	}
```

### [Op4j](www.op4j.org)
[Op4JResort.java](src/main/java/com/naver/helloworld/resort/service/Op4JResort.java)

```java
public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAllGuest();
	return Op.on(all)
		.removeAllFalse(new IFunction<Guest, Boolean>() {
			public Boolean execute(Guest g, ExecCtx ctx) throws Exception {
				return company.equals(g.getCompany());
			}
		})
		.sortBy(new IFunction<Guest, Integer>() {
			public Integer execute(Guest g, ExecCtx ctx) throws Exception {
				return g.getGrade();
			}
		})
		.map(new IFunction<Guest, String>() {
			public String execute(Guest g, ExecCtx ctx) throws Exception {
				return g.getName();
			}
		}).get();
}
```

### [Lambdaj](https://code.google.com/p/lambdaj)
[LambdaJResort.java](src/main/java/com/naver/helloworld/resort/service/LambdaJResort.java)

```java
import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;
...

public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAll();
	return LambdaCollections.with(all)
		.retain(having(on(Guest.class).getCompany(), equalTo(company)))
		.sort(on(Guest.class).getGrade())
		.extract(on(Guest.class).getName());
}
```

### [Functional Java](http://functionaljava.org/)
[FunctionalJavaResort.java](src/main/java/com/naver/helloworld/resort/service/FunctionalJavaResort.java)

```java
public List<String> findGuestNamesByCompany(String company) {
	List<Guest> all = repository.findAll();

	Collection<String> mapped = Stream.iterableStream(all)
		.filter(new F<Guest, Boolean>() {
			public Boolean f(Guest g){
				return company.equals(g.getCompany());
			}
		})
		.sort(Ord.ord(
			new F<Guest, F<Guest, Ordering>>() {
				public F<Guest, Ordering> f(final Guest a1) {
					return new F<Guest, Ordering>() {
						public Ordering f(final Guest a2) {
							int x =  Integer.compare(a1.getGrade(), a2.getGrade());
							return x < 0 ? Ordering.LT : x == 0 ? Ordering.EQ : Ordering.GT;
					}
				};
			}
		}))
		.map(new F<Guest, String>() {
			public String f(Guest g) {
				return g.getName();
			}
		})
		.toCollection();
	return new ArrayList<String>(mapped);
}
```

### [Apache Commons Collections](http://commons.apache.org/proper/commons-collections/)
[CommonsCollectionsResort.java](src/main/java/com/naver/helloworld/resort/service/CommonsCollectionsResort.java)

```java
public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAll();
	List<Guest> filtered = ListUtils.select(all, new Predicate<Guest>() {
		public boolean evaluate(Guest g) {
			return company.equals(g.getCompany());
		}
	});
	Collections.sort(filtered, new Comparator<Guest>() {
		public int compare(Guest o1, Guest o2) {
			return Integer.compare(o1.getGrade(), o2.getGrade());
		}
	});
	Collection<String> names = CollectionUtils.collect(filtered, new Transformer<Guest, String>(){
		public String transform(Guest g) {
			return g.getName();
		}
	});
	return new ArrayList<>(names);
}
```

### [Jedi](http://jedi.codehaus.org/)
[JediResort.java](src/main/java/com/naver/helloworld/resort/service/JediResort.java)

```java
public List<String> findGuestNamesByCompany(final String company) {
	List<Guest> all = repository.findAll();
	List<Guest> filtered = FunctionalPrimitives.select(all, new Filter<Guest>() {
		public Boolean execute(Guest g) {
			return company.equals(g.getCompany());
		}
	});
	List<Guest> sorted = Comparables.sort(filtered, new Functor<Guest, Integer>() {
		public Integer execute(Guest g) {
			return g.getGrade();
		}
	});
	return FunctionalPrimitives.map(sorted, new Functor<Guest, String>() {
		public String execute(Guest g) {
			return g.getName();
		}
	});
}
```

## Implementations by other JVM languages
- Groovy : 2.3.9
- Scala :  2.11.4
- Kotlin : 0.10.195
- Xtend : 2.7
- Ceylon : 1.1.0

### [Groovy](http://groovy.codehaus.org/)
[GroovyAdvancedResort.groovy](src/main/groovy/com/naver/helloworld/resort/service/GroovyAdvancedResort.groovy)

```groovy
List<String> findGuestNamesByCompany(String company) {
	List<Guest> all = repository.findAll()
	all.findAll { it.company == company }
		.sort { it.grade }
		.collect { it.name }
}
```

### [Scala](http://www.scala-lang.org/)
[ScalaAdvancedResort.scala](src/main/scala/com/naver/helloworld/resort/service/ScalaAdvancedResort.scala)

```scala
import scala.collection.JavaConversions._
...

	def findGuestNamesByCompany(company: String): java.util.List[String] = {
		val all = repository.findAll
		all.filter ( _.getCompany == company)
			.sortBy ( _.getGrade )
			.map ( _.getName )
	}
```

### [Kotlin](http://kotlinlang.org)
[KotlinAdvancedResort.kt](src/main/kotlin/com/naver/helloworld/resort/service/KotlinAdvancedResort.kt)

```kotlin

	override fun findGuestNamesByCompany(company: String): List<String> {
		val all = repository.findAll()
		return all.filter { it.getCompany() == company }
			.sortBy { it.getGrade() }
			.map { it.getName() }
	}
```

### [Xtend](http://www.eclipse.org/xtend/)
[XtendAdvancedResort.xtend](src/main/xtend/com/naver/helloworld/resort/service/XtendAdvancedResort.xtend)

```xtend
override findGuestNamesByCompany(String aCompany) {
	val all = repository.findAll()
	all.filter [company == aCompany]
		.sortBy[grade]
		.map[name]
}
```

### [Ceylon](http://ceylon-lang.org/)
[resort.ceylon](src/main/ceylon/com/naver/helloworld/resort/service/resort.ceylon)

```ceylon
import ceylon.interop.java { CeylonIterable }
import java.util {JList = List, JArrayList = ArrayList }
import java.lang {JString = String}

...

	shared actual JList<JString> findGuestNamesByCompany(String company) {
		value all = repository.findAll() ;
		value names = CeylonIterable(all)
			.filter((Guest g) => g.company == company)
			.sort(byIncreasing((Guest g) => g.grade.intValue()))
			.map((Guest g) => g.name);

		value jnames = JArrayList<JString>();
		for (name in names) {jnames.add(JString(name));}
		return jnames;
	}
```

## Implementations by modern Java
[ModernJavaAdvancedResort.java](src/main/java/com/naver/helloworld/resort/service/ModernJavaAdvancedResort.java)

```java
public List<String> findGuestNamesByCompany(String company) {
	List<Guest> guests = repository.findAll();
	return guests.stream()
		.filter(g -> company.equals(g.getCompany()))
		.sorted(Comparator.comparing(Guest::getGrade))
		.map(Guest::getName)
		.collect(Collectors.toList());
}
```

# Refactoring by lambda expressions

## Async Servlet

### Classic Java
[ClassicAsyncServlet.java](src/main/java/com/naver/helloworld/web/ClassicAsyncServlet.java)

```java
public void doGet(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	final AsyncContext asyncContext = request.startAsync();
		asyncContext.start(new Runnable() {
		public void run() {
			// long running job
			asyncContext.dispatch("/status.jsp");
		}
		});
}
```

### Modern Java
[ModernAsyncServlet.java](src/main/java/com/naver/helloworld/web/ModernAsyncServlet.java)

```java
public void doGet(final HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
	AsyncContext asyncContext = request.startAsync();
	asyncContext.start(() -> {
		// long running job
		asyncContext.dispatch("/status.jsp");
		});
}
```

## Spring JDBC
### Classic Java
[ClassicJdbcRepository.java](src/main/java/com/naver/helloworld/resort/repository/ClassicJdbcRepository.java)

```java
public List<Guest> findAll() {
	return jdbcTemplate.query(SELECT_ALL, new RowMapper<Guest>(){
		public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
			return  new Guest (
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("company"),
				rs.getInt("grade")
			);
	}
  });
}
```

### Modern Java
[ModernJdbcRepository.java](src/main/java/com/naver/helloworld/resort/repository/ModernJdbcRepository.java)

```java
public List<Guest> findAll() {
	return jdbcTemplate.query(SELECT_ALL,
		(rs, rowNum) ->new Guest (
			rs.getInt("id"),
			rs.getString("name"),
			rs.getString("company"),
			rs.getInt("grade")
			)
  );
}
```

### Event bindings in Android
### Classic Java
[ClassicFragment.java](src/main/java/com/naver/helloworld/resort/android/ClassicFragment.java)

```java
Button calcButton = (Button) view.findViewById(R.id.calcBtn);
Button sendButton = (Button) view.findViewById(R.id.sendBtn);

calcButton.setOnClickListener(new OnClickListener() {
	public void onClick(View view) {
		calculate();
	}
});
sendButton.setOnClickListener(new OnClickListener() {
	public void onClick(View view) {
		send();
	}
});
```

### Modern Java
[ModernFragment.java](src/main/java/com/naver/helloworld/web/ModernAsyncServlet.java)

```java
Button calcButton = (Button) view.findViewById(R.id.calcBtn);
Button sendButton = (Button) view.findViewById(R.id.sendBtn);

calcButton.setOnClickListener(v -> calculate());
sendButton.setOnClickListener(v -> send());
```

# Frameworks using lambda expressions
### [Lambda Behave](http://richardwarburton.github.io/lambda-behave/)
[ResortServiceSpec.java](src/test/java/com/naver/helloworld/resort/service/ResortServiceSpec.java)

```java
@RunWith(JunitSuiteRunner.class)
public class ResortServiceSpec {{
	GuestRepository repository = new MemoryRepository();
	ResortService service = new ModernJavaResort(repository);

	describe("ResortService with modern Java", it -> {
		it.isSetupWith(() -> {
			repository.save(
					new Guest(1, "jsh", "Naver", 15),
					new Guest(2, "hny", "Line", 10),
					new Guest(3, "chy", "Naver", 5)
				);

		});
		it.isConcludedWith(repository::deleteAll);

		it.should("find names of guests by company ", expect -> {
			List<String> names = service.findGuestNamesByCompany("Naver");
			expect.that(names).isEqualTo(Arrays.asList("chy","jsh"));
		});
	});
}}
```

### [Jinq](http://www.jinq.org/)
[JinqResort.java](src/main/java/com/naver/helloworld/resort/service/JinqResort.java)

```java
private EntityManager em;
@Autowired
public JinqResort(EntityManager em) {
	this.em = em;
}
private <T> JinqStream<T> stream(Class<T> clazz) {
	return new JinqJPAStreamProvider(em.getEntityManagerFactory()).streamAll(em, clazz);
}

public List<String> findGuestNamesByCompany(String company) {
	return stream(Guest.class)
		.where(g -> g.getCompany().equals(company))
		.sortedBy(Guest::getGrade)
		.select(Guest::getName)
		.toList();
}
```

A query generated by JinqResort

```sql
	Hibernate: select guest0_.id as id1_0_, guest0_.company as company2_0_, guest0_.grade as grade3_0_, guest0_.name as name4_0_ from guest guest0_ where guest0_.company=? order by guest0_.grade ASC limit ?
```

### [Spark](http://www.sparkjava.com/)
[SparkServer.java](src/main/java/com/naver/helloworld/web/SparkServer.java)

```java
import static spark.Spark.*;

import com.naver.helloworld.resort.service.ResortService;

public class SparkServer {
	public static void main(String[] args) {
		get("/guests/:company", (request, response) -> {
			String company = request.params(":company");
			return "No guests from " + company;
		});
	}
}
```

[ResortServer.java](src/main/java/com/naver/helloworld/resort/ResortServer.java) (Spark + Spring)

```java
@SpringBootApplication
public class ResortServer {
	@Autowired
	private ResortService service;

	public void start() {
		get("/guests/:company", (request, response) -> {
			String company = request.params(":company");
			List<String> names = service.findGuestNamesByCompany(company);
			return "Guests from " + company + " : " + names;
		});
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ResortServer.class);
		context.getBean(ResortServer.class).start();
	}
}
```
