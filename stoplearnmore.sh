ppid=`cat runningpid.log`
echo $ppid
pid=`ps -ef|grep play|awk '{if($3=='$ppid'){print $2}}'`
if ["$pid" -eq ""]
then
	echo "no process"
else
	echo "play pid is "$pid
	echo "kill process"
	kill -9 $pid

	checkpid=`ps -ef|grep play|awk '{if($3=='$ppid'){print $2}}'`
	if ["$checkpid" -eq ""]
	then
		echo "finish to stop learnmore server"
	fi
fi

