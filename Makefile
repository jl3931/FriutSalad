all: fruit/sim/Fruit.class fruit/g2/player.class

fruit/g2/player.class: fruit/g2/*.java fruit/sim/Player.java
	javac $^

fruit/sim/Fruit.class: fruit/sim/*.java
	javac $^

.PHONY: rungui
rungui:
	java -ea fruit.sim.Fruit players.list 12 fruit/g2/hitandmiss.txt true

.PHONY: run
run:
	java -ea fruit.sim.Fruit players.list 12 uniform.txt false

.PHONY: runtour
runtour:
	java -ea fruit.sim.Fruit players.list 12 uniform.txt false false $(n)

.PHONY: clean
clean:
	$(RM) fruit/sim/*.class
	$(RM) fruit/g2/*.class
	$(RM) fruit/g2/*~
	$(RM) fruit/dumb/*.class
