#!/bin/sh

#警告!!!：该脚本stop部分使用系统kill命令来强制终止指定的java程序进程。
#在杀死进程前，未作任何条件检查。在某些情况下，如程序正在进行文件或数据库写操作，
#可能会造成数据丢失或数据不完整。如果必须要考虑到这类情况，则需要改写此脚本，

# 设置时间变量
CTIME=$(date "+%Y-%m-%d-%H-%M")

# 设置Java环境
JAVA_HOME=/usr/local/java/jdk1.8.0_144

# 设置应用名称
PROJECT_NAME=blog-project

# 设置应用名称
APP_NAME=my-blog

# 配置文件路径
CONFIG_FILE_PATH=/appdata/"$PROJECT_NAME"/config

# 配置文件
CONFIG_FILE=application-dev.properties

# 日志目录
LOG_PATH=/applog/"${PROJECT_NAME}"

# 临时代码目录，用来修改配置文件和编译打包代码
TMP_DIR=/tmp/"$PROJECT_NAME"

# 用来存放项目jar文件
APP_HOME=/appdata/"$PROJECT_NAME"

#执行程序启动所使用的系统用户，考虑到安全，不推荐使用root帐号
RUNNING_USER=root

#需要启动的Java主程序（main方法类）
APP_MAINCLASS=

#java虚拟机启动参数
JAVA_OPTS="-Xmx1024m -Djava.awt.headless=true"

psid=0

checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_NAME`
   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}

###################################
#(函数)打包
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示程序已启动
#3. 如果程序没有被启动，则执行启动命令行
#4. 启动命令执行后，再次调用checkpid函数
#5. 如果步骤4的结果能够确认程序的pid,则打印[OK]，否则打印[Failed]
#注意：echo -n 表示打印字符后，不换行
###################################
moveExecuteFile(){
   echo "移动文件到执行目录"
#   mvn clean package && cp target/"$APP_NAME".jar "$APP_HOME"/$APP_NAME.jar
    cp target/"$APP_NAME".jar "$APP_HOME"/$APP_NAME.jar
}

###################################
#(函数)启动程序
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示程序已启动
#3. 如果程序没有被启动，则执行启动命令行
#4. 启动命令执行后，再次调用checkpid函数
#5. 如果步骤4的结果能够确认程序的pid,则打印[OK]，否则打印[Failed]
#注意：echo -n 表示打印字符后，不换行
###################################

start() {
   checkpid
   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_NAME already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_NAME ..."
	  nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_HOME/$APP_NAME.jar --spring.profiles.active=dev --spring.config.location=$CONFIG_FILE_PATH/$CONFIG_FILE >> $LOG_PATH/$APP_NAME"_"$(date "+%Y-%m-%d").log 2>&1 &
      echo "开始启动"
#      sleep 15s
      checkpid
      if [ $psid -ne 0 ]; then
         echo "(pid=$psid) [OK]"
      else
         echo "[Failed]"
      fi
   fi
   exit
}

###################################
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则开始执行停止，否则，提示程序未运行
#3. 使用kill -9 pid命令进行强制杀死进程
#4. 执行kill命令行紧接其后，马上查看上一句命令的返回值: $?
#5. 如果步骤4的结果$?等于0,则打印[OK]，否则打印[Failed]
#6. 为了防止java程序被启动多次，这里增加反复检查进程，反复杀死的处理（递归调用stop）。
#注意: 在shell编程中，"$?" 表示上一句命令或者一个函数的返回值
###################################
stop() {
   checkpid
   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_NAME...(pid=$psid) "
      kill -9 $psid
      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi

      checkpid
      if [ $psid -ne 0 ]; then
         stop
      fi
   else
      echo "================================"
      echo "warn: $APP_NAME is not running"
      echo "================================"
   fi
}

###################################
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示正在运行并表示出pid
#3. 否则，提示程序未运行
###################################
status() {
   checkpid
   if [ $psid -ne 0 ];  then
      echo "$APP_NAME is running! (pid=$psid)"
   else
      echo "$APP_NAME is not running"
   fi
}

###################################
#(函数)打印系统环境参数
###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_HOME=$APP_HOME"
   echo "APP_NAME=$APP_NAME"
   echo "****************************"
}

###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
   'move')
     moveExecuteFile
     ;;
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status|info}"
     exit 1
esac
exit 0
