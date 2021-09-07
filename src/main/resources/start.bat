@SET DB.USER=root
@SET DB.PASS=pass
@SET DB.HOST=localhost
@SET DB.PORT=50000
@SET DB.NAME=SAMPLE


"C:\Program Files (x86)"\Java\jdk1.8.0_181\bin\java -Dserver.port=8080 -Dspring.profiles.active=dev -jar simplecs-1.0-SNAPSHOT.jar > log.txt

pause