#!/bin/sh

#########################################################################################################
# 有三种启动方式，此为其中一种
# @auth tyuan.design 										#
#													#
# 使用前：												#
#													#
# 使用前先将变量appName中的XXXX替换成应用名，同一服务器下应用名称必须唯一，只能是字母、数字和下划线	#
#													#
# 执行脚本：												#
# sh controller.sh ${param}										#
#													#
# ${param}有四种取值：											#
# 1. start	启动应用										#
# 2. stop	停止应用										#
# 3. restart	重启应用										#
# 4. status	查看应用状态										#
#													#
# 注意：（主jar包就是包含main方法启动类的jar包）							#
# 1.主jar包必须将依赖jar和主类信息都写入到manifest文件中；						#
# 2.主jar包与当前脚本放在同级目录；									#
# 3.依赖的jar包放在下级lib目录下；									#
#########################################################################################################
echo "############################      START     #################################"

APP_HOME=$(cd `dirname $0`; pwd)
appName="DappName=micro-enterprise"
APP_OPTS="--spring.profiles.active=test"
JAVA_OPTS="-$appName -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8800,server=y,suspend=n -server -Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$APP_HOME/dump -XX:ErrorFile=$APP_HOME/logs/hs_err_pid%p.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xloggc:$APP_HOME/logs/gc_pid%p.log -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+UnlockDiagnosticVMOptions"

stop(){
	echo ""
	echo ""
	# 获取进程信息
	pr=`ps ax | grep java | grep $appName | egrep -v "grep"`
	echo "-------------current process status------------------------"
	echo $pr
	# 停止进程
	if [ -z "$pr" ];then
		echo -e "[ \033[31m \033[5m process is not running \033[0m ]"
	else
		echo ""
		echo "-------------stop process----------------------------------"
		pr_num=`ps ax | grep java | grep $appName | egrep -v "grep" | awk '{print $1}'`
		echo "process is running PID:$pr_num"
		echo -e "killing process $pr_num"
		kill  $pr_num
		sleep 2s
		while true;do
		        pr=`ps ax | grep java | grep $appName | egrep -v "grep" `
			if [ -z "$pr" ];then
				echo -e "[ \033[32m \033[5m process stop success \033[0m ]"
				break
			else
				echo -e "[ \033[31m \033[5m process is running,waiting to close \033[0m ]"
				kill  $pr_num
				sleep 2s
			fi
		done
	fi
}

start(){
	echo ""
	echo ""
	pr=`ps ax | grep java | grep $appName | egrep -v "grep" `
	echo ""
	echo "-------------current process status------------------------"
	echo $pr
	if [ -z "$pr" ];then
		echo "-------------starting process------------------------"
		nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $jarFileName  $APP_OPTS > $APP_HOME/nohup.out 2>&1 &
		sleep 3s
		pr=`ps ax | grep java | grep $appName | egrep -v "grep" `
		if [ -z "$pr" ];then
			echo -e "[ \033[31m  \033[5m process start fail \033[0m ]"
		else
			echo -e "[ \033[32m  \033[5m process start success \033[0m ]"
			echo "process info:"
			echo $pr
		fi
	else
		echo -e "[ \033[31m \033[5m process alread started \033[0m ]"
	fi
}

status(){
	echo ""
	echo ""
	echo "-------------current process status------------------------"
	pr=`ps ax | grep java | grep $appName | egrep -v "grep" `
	if [ -z "$pr" ];then
		echo -e "[ \033[32m \033[5m process is not running \033[0m ]"
	else
		pr_num=`ps ax | grep java | grep $appName | egrep -v "grep" | awk '{print $1}'`
		echo -e "[ \033[32m \033[5m process is running PID:$pr_num \033[0m ]"
		echo "process info："
		echo $pr
	fi
}

echo ""
echo ""


jarFileName=`find $APP_HOME  -maxdepth 1 -name "*.jar"`
jarFileNum=`find $APP_HOME  -maxdepth 1 -name "*.jar"|wc -l`
if [ $jarFileNum -ne 1 ];then
        echo -e "\033[43m \033[31m [ERRPR]must only one jar package in dir \"$APP_HOME\",there are $jarFileNum jar package \033[0m"
        echo $jarFileName
else
	echo "JAR=\"$jarFileName\""
	echo "APP_Name = ${appName##*DappName=}"
	echo "APP_HOME = $APP_HOME"
	echo "JAVA_HOME = $JAVA_HOME"

	case "$1" in
	  status)
		status
	        ;;
	  start)
		start
	        ;;
	  stop)
		stop
	        ;;
	  restart)
	        stop
		sleep 2
	        start
		;;
	  *)
		echo -e "\033[43m \033[31m [ERROR] Please enter the parameters,it can be \"start\" or \"stop\" or \"restart\" or \"status\" \033[0m"
	        echo example:
	        echo "sh controller.sh status"
		;;
	esac
fi

echo "注意如果是线上环境，请先去掉远程debug配置"
