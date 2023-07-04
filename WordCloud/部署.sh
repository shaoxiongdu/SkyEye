appName="word-cloud.jar"

# 编译
mvn clean
mvn package assembly:single

cd target
rm WordCloud-1.0-SNAPSHOT.jar
mv WordCloud-1.0-SNAPSHOT-jar-with-dependencies.jar word-cloud.jar

# 上传文件  确保已经配置了SSH公钥身份验证

scp './word-cloud.jar' root@192.168.31.40:'/home/jar/'

# 执行
ssh root@192.168.31.40 << EOF

# 后台启动上传的 JAR 文件
cd /home/jar
nohup java -jar ./word-cloud.jar > /dev/null 2>&1 &
EOF
