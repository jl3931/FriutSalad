all: fruit/dumb/player.class fruit/sim/UniformFruitGenerator.class fruit/sim/Fruit.class fruit/g2/player.class fruit/g2/FruitGenerator.class

fruit/dumb/player.class: fruit/dumb/*.java fruit/sim/Player.java
	javac $^

fruit/g2/player.class: fruit/g2/*.java fruit/sim/Player.java
	javac $^

fruit/g2/FruitGenerator.class: fruit/g2/FruitGenerator.java fruit/sim/FruitGenerator.java
	javac $^

fruit/sim/UniformFruitGenerator.class: fruit/sim/UniformFruitGenerator.java fruit/sim/FruitGenerator.java
	javac $^

fruit/sim/Fruit.class: fruit/sim/*.java
	javac $^

.PHONY: rungui
rungui:
	java fruit.sim.Fruit players.list 12 fruit.sim.UniformFruitGenerator true

.PHONY: run
run:
	java fruit.sim.Fruit players.list 12 fruit.sim.UniformFruitGenerator false

.PHONY: clean
clean:
	$(RM) fruit/sim/*.class
	$(RM) fruit/g2/*.class
	$(RM) fruit/g2/*~
	$(RM) fruit/dumb/*.class