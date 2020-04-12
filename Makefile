setup:
	gradle


package:
	./gradlew war

run-local:
	./gradlew jettyRunWar
