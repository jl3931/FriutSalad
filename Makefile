all: fruit/dumb/player.class fruit/sim/UniformFruitGenerator.class fruit/sim/Fruit.class

fruit/dumb/player.class: fruit/dumb/*.java
	javac $^

fruit/sim/UniformFruitGenerator.class: fruit/sim/UniformFruitGenerator.java fruit/sim/FruitGenerator.java
	javac $^

fruit/sim/Fruit.class: fruit/sim/*.java
	javac $^

.PHONY: run
run:
	java fruit.sim.Fruit players.list 12 fruit.sim.UniformFruitGenerator true

.PHONY: clean
clean:
	$(RM) fruit/sim/*.class
	$(RM) fruit/dumb/*.class