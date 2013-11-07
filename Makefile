all: fruit/sim/Fruit.class fruit/g2/player.class fruit/g10/player.class fruit/g2_new/player.class

fruit/g2/player.class: fruit/g2/*.java fruit/sim/Player.java
	javac $^

fruit/g10/player.class: fruit/g10/*.java fruit/sim/Player.java
	javac $^

fruit/g2_new/player.class: fruit/g2_new/*.java fruit/sim/Player.java
	javac $^

fruit/sim/Fruit.class: fruit/sim/*.java
	javac $^

.PHONY: rungui
rungui: all
	java -ea fruit.sim.Fruit players.list 12 uniform.txt true

.PHONY: run
run: all
	java -ea fruit.sim.Fruit players.list 12 uniform.txt false

.PHONY: runtour
runtour: all
	java -ea fruit.sim.Fruit players.list 12 $(dist) false false $(n)

.PHONY: clean
clean:
	$(RM) fruit/sim/*.class
	$(RM) fruit/g2/*.class
	$(RM) fruit/g2/*~
	$(RM) fruit/dumb/*.class
	$(RM) fruit/g10/*.class
	$(RM) fruit/g10/*~
