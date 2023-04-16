
mkdir target/extracted
java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted
fly deploy --local-only