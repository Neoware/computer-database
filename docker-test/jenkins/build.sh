docker stop maven-build && docker rm maven-build || echo 'No container named maven-build to stop and delete'
docker run  --name maven-build -itd neoware/maven-build bash 
docker cp ${WORKSPACE} maven-build:/home
docker exec -i maven-build mvn -f /home/workspace/pom.xml clean install
docker cp maven-build:/home/workspace/target/*.war /home 

