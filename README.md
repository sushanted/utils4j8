# utils4j8

Utility methods to reuse common patterns in java 8 fluent code, to make the code smaller, robust and beautiful. 

For example: 
    Optional.ofNullable(person)
			    .map(
            function(Person::getName)
            .ifNullSwitchTo(“Unknown”)
          )
			    .map(String::toUpperCase)
			    .ifPresent(Syste.out::println);

In above code the ‘function’ method in utils4j8 provides the facility to default the value of the person name if it is null and apply all the further chained processing on it, same as if it were present.

