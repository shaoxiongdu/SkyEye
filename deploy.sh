# 编译
mvn clean
mvn package -DskipTests

# 上传文件  确保已经配置了SSH公钥身份验证
cd ./target
mv ./SkyEye-0.0.1-SNAPSHOT.jar ./skyEyeSystem.jar
scp './skyEyeSystem.jar' root@114.115.209.223:'/home/jar/'

# 执行
ssh root@114.115.209.223 << EOF
# 停止当前运行在80端口的应用
sudo fuser -k 80/tcp
cd /home/jar
# 后台启动上传的 JAR 文件
nohup java -jar ./skyEyeSystem.jar --spring.profiles.active=HWCloud > /dev/null 2>&1 &
EOF

echo "http://114.115.209.223/swagger-ui/index.html"