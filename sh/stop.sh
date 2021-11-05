APP_HOME=$(cd `dirname $0`; pwd)
APP_NAME="sme"

echo ""
pr=`ps ax | grep java | grep $APP_NAME | egrep -v "grep"`
echo "-------------current process status------------------------"
echo $pr
if [ -z "$pr" ];then
        echo "warn: process is not running"
        exit 2
else
        echo ""
        echo "-------------stop process----------------------------------"
        pr_num=`ps ax | grep java | grep $APP_NAME | egrep -v "grep" | awk '{print $1}'`
        echo "process is running PID:$pr_num"
        echo -e "killing process $pr_num"
        kill -9 $pr_num
        sleep 3
        pr=`ps ax | grep java | grep $APP_NAME | egrep -v "grep" `
        if [ -z "$pr" ];then
                echo "process stop success"
        else
                echo "process stop failed"
                echo "process info："
                echo $pr
                exit 1;
        fi
fi
