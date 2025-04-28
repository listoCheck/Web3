[//]: # (REMOVE PREVIOUS INSTANCE)
docker rm -f simple_postgres_instance

[//]: # (START POSTGRES IN DOCKER)
docker run --name simple_postgres_instance -e POSTGRES_PASSWORD=123456 -p 5432:5432 -d postgres:16

[//]: # ()
sshpass -p *** ssh -L 25102:localhost:25102 s408145@helios.cs.ifmo.ru -p 2222\
sshpass -p *** ssh -L 25104:localhost:25104 s408145@helios.cs.ifmo.ru -p 2222

jconsole -J-Djava.class.path=/Users/macbook/Desktop/jboss-cli-client-amuz.jar
service:jmx:remote+http://0.0.0.0:25104
