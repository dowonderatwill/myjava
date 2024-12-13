All about maps
  - 1. In a map get all the enteries where the key size is some value.
  ```code
  HashMap<> ret = new HashMap<>();
  HashMap<> m = new HashMap<>();
  m.entrySet().stream().filter( e-> e.getKey().size()==4 ).forEach(ret.put(e.getKey(),e.getValue()));
  ```
  - 2. Reduce each value by 1
  ```code
  m.replaceAll(v->v-1);
  ```
  - 3. Remove all the enteries from map where the value is 0
  ```code
  m.entrySet().removeIf(e->e.getValue() == 0);
  ```

Plain java webservice
- https://examples.javacodegeeks.com/java-development/enterprise-java/jws/jax-ws-endpoint-example/
- 1. Create Interface, Implement it and then publish it:
```code
   Endpoint ep = Endpoint.create(new CalculatorServerImpl());  
   ep.publish("http://127.0.0.1:10000/calcServer");
```

