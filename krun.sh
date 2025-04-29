echo "Running Maven project..."

mvn test -Dtest=KarateTest#runKarateTests
mvn test -Dtest=KarateTest#runKarateUITests
mvn test -Dtest=KarateTest#UserApiSimulation
